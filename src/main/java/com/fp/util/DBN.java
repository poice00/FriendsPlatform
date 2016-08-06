package com.fp.util;

import java.util.Random;

public class DBN {
	public int N;
	public int n_ins;
	public int[] hidden_layer_sizes;
	public int n_outs;
	public int n_layers;
	public HiddenLayer[] sigmoid_layers;
	public RBM[] rbm_layers;
	public LogisticRegression log_layer;
	public Random rng;

	public static double sigmoid(double x) {
		return 1.0 / (1.0 + Math.pow(Math.E, -x));//次方函数，表示Math.E的-x次方
	}
	
	//DBN 的构造函数  N 为样本的个数， n-ins 为特征个数， hidden_layer_sizes 为 隐藏层的结构， n-outs 为输出维数， n-layers 为隐藏层个数， rng 为随机数实例
	public DBN(int N, int n_ins, int[] hidden_layer_sizes, int n_outs, int n_layers, Random rng) {
		int input_size;
		
		this.N = N;  // 赋值样本数目
		this.n_ins = n_ins; //赋值特征个数
		this.hidden_layer_sizes = hidden_layer_sizes;  //赋值隐藏层结构
		this.n_outs = n_outs;   // 赋值输出维数
		this.n_layers = n_layers;   // 赋值隐藏层数目
		
		this.sigmoid_layers = new HiddenLayer[n_layers]; // 声明两个隐藏层
		this.rbm_layers = new RBM[n_layers];    //声明两个RBM对应每个隐藏层

		if(rng == null)	this.rng = new Random(1234);  // 获取一个随机数值
		else this.rng = rng;		
		
		// construct multi-layer  初始化每个隐藏层
		for(int i=0; i<this.n_layers; i++) {
			if(i == 0) {
				input_size = this.n_ins;   //第一层隐藏层的输入为样本的特征的个数
			} else {
				input_size = this.hidden_layer_sizes[i-1]; // 后面的隐藏层的输入为上一层隐藏层的输出，也就是上一层的隐藏层节点的个数。
			}
			 // sigmoid 层是用来计算的， rbm 是用来调整 w , b , c 的
			// construct sigmoid_layer  初始化每个隐藏层  ， 初始化做的事情就是给W和b赋随机值
			this.sigmoid_layers[i] = new HiddenLayer(this.N, input_size, this.hidden_layer_sizes[i], null, null, rng);
			
			// construct rbm_layer   初始化玻尔兹曼机，其实也就是初始化，W， b , c 其中，w , b 用的是hiddenlayer的
			this.rbm_layers[i] = new RBM(this.N, input_size, this.hidden_layer_sizes[i], this.sigmoid_layers[i].W, this.sigmoid_layers[i].b, null, rng);
		}
		//在完成每一层的构建之后，构建一个输出的逻辑回归层
		// layer for output using LogisticRegression， 参数为样本个数N， 输入为网络结构最后一层的输出数， 输出为DBM网络设置的输出维数
		this.log_layer = new LogisticRegression(this.N, this.hidden_layer_sizes[this.n_layers-1], this.n_outs);
	}
	
	//对DBN网络进行一个预训练，目的是为每一层先构造更好的W和b， 先使得网络更好的拟合样本的分布，类似于先把点放在最后值的附近
	public void pretrain(int[][] train_X, double lr, int k, int epochs) {
		//输入训练样本， 学习率lr ， CD-k =1 , epochs=1000
		int[] layer_input = null ;
		int prev_layer_input_size;
		int[] prev_layer_input;
				
		for(int i=0; i<n_layers; i++) {  // layer-wise	迭代每一个层	 
			for(int epoch=0; epoch<epochs; epoch++) {  // training epochs  每个层都迭代优化epochs次 
				for(int n=0; n<N; n++) {  // input x1...xN  每一层都遍历每个训练样本 ， 这种方式相当于是随机梯度下降
					// layer input
					for(int l=0; l<=i; l++) { //从前面训练好的每一层开始迭代 ，假设有3层，i=2 ， 0，1，2 迭代3次
						if(l == 0) { // l=0 的时候只是获取数据的特征
							layer_input = new int[n_ins]; //第一层的输入维度为样本的特征数
							for(int j=0; j<n_ins; j++) layer_input[j] = train_X[n][j]; //遍历第i个样本的第j个特征赋值给layer _input
							// 也就是第一层处理的数据是样本的原始的特征。
						} else {    // 如果不是第一层的话，本层处理的数据是上一层的输出
							if(l == 1) prev_layer_input_size = n_ins; // l = 1 的时候输入的维度为原始数据的特征数
							else prev_layer_input_size = hidden_layer_sizes[l-2];
							
							prev_layer_input = new int[prev_layer_input_size]; // 声明这一层的输入数据维度
							for(int j=0; j<prev_layer_input_size; j++) prev_layer_input[j] = layer_input[j];
							//这一层的输入数据是上一层的输出，l=0的时候pre_layer_input 为 traning data
							
							layer_input = new int[hidden_layer_sizes[l-1]]; // layer_input其实就是这一层的输出
							
							//给定上一层的输出数据作为本层的输入数据，计算出本层的输出， 就只是单纯的利用rb,修改后的w ,b来作出计算
							sigmoid_layers[l-1].sample_h_given_v(prev_layer_input, layer_input);
						}
					}
					// 在rbm 层上 ， 根据输入 layer_input 和学习率lr ，对　 w  b  c 进行调整  , 同时每一个数据都要进行调整
					rbm_layers[i].contrastive_divergence(layer_input, lr, k);
				} // end for every training data
			}//end for epochs
		}// end for layer-wise
	}
	
	// 使用finetune 进行微调 ， 这里是有监督学习
	public void finetune(int[][] train_X, int[][] train_Y, double lr, int epochs) {
		int[] layer_input = new int[0];
		// int prev_layer_input_size;
		int[] prev_layer_input = null ;
		
		for(int epoch=0; epoch<epochs; epoch++) {//迭代epochs 次
			for(int n=0; n<N; n++) {  //遍历所有的输入数据样本
				// layer input
				for(int i=0; i<n_layers; i++) {
					if(i == 0) {
						prev_layer_input = new int[n_ins]; // 如果是第一层的话，输入就是数据样本的维度
						for(int j=0; j<n_ins; j++) prev_layer_input[j] = train_X[n][j]; //获取输入数据
					} else {
						prev_layer_input = new int[hidden_layer_sizes[i-1]];
						for(int j=0; j<hidden_layer_sizes[i-1]; j++) prev_layer_input[j] = layer_input[j];
					}
					
					layer_input = new int[hidden_layer_sizes[i]];
					//第i层的sigmoid 层计算出本层的输出，作为下一层的输入layer_input
					sigmoid_layers[i].sample_h_given_v(prev_layer_input, layer_input);
				} // end for iter layer 
				//一个样本从头扫到尾， 遍历所有的层最后的输出保存在layer_input当中
				log_layer.train(layer_input, train_Y[n], lr); //log_layer是逻辑回归的对象， 用layer_input 和label 来做逻辑回归
			}  // end for iter data
			// lr *= 0.95;
		}// end for epochs 
	}
	
	public void predict(int[] x, double[] y) {  // 这里一次只处理一个样本
		double[] layer_input = new double[0];
		// int prev_layer_input_size;
		double[] prev_layer_input = new double[n_ins];
		for(int j=0; j<n_ins; j++) prev_layer_input[j] = x[j]; //最开始的输入是特征x
	
		double linear_output;
		
		
		// layer activation  迭代每一层
		for(int i=0; i<n_layers; i++) {  
			layer_input = new double[sigmoid_layers[i].n_out]; //后面层的输入是该层的输出
			
			for(int k=0; k < sigmoid_layers[i].n_out; k++) {  //遍历的是一个[n-out][n-in]的数组
				linear_output = 0.0; 
				for(int j=0; j<sigmoid_layers[i].n_in; j++) {
					linear_output += sigmoid_layers[i].W[k][j] * prev_layer_input[j];
				}
				linear_output += sigmoid_layers[i].b[k];
				layer_input[k] = sigmoid(linear_output);
			}
			
			if(i < n_layers-1) { // 上层的输出是layer_input , 做为下一层的输入
				prev_layer_input = new double[sigmoid_layers[i].n_out];
				for(int j=0; j<sigmoid_layers[i].n_out; j++) prev_layer_input[j] = layer_input[j];
			}
		} // end for iter layer
		
		for(int i=0; i<log_layer.n_out; i++) { //到最后一层的时候，做一个逻辑回归
			y[i] = 0;
			for(int j=0; j<log_layer.n_in; j++) {
				y[i] += log_layer.W[i][j] * layer_input[j];
			}
			y[i] += log_layer.b[i];
		}
		
		log_layer.softmax(y); //然后softmax 获得一个归一话的结果
	}
	
	private static void test_dbn() {
		Random rng = new Random(123);
		
		double pretrain_lr = 0.1; //pre-training 的学习率初始的时候设置为0.1
		int pretraining_epochs = 1000;  
		int k = 1;
		double finetune_lr = 0.1; // fine-tune 的学习率为0。1
		int finetune_epochs = 500;   //fine-turne 的迭代次数
		
		int train_N = 6;  // 训练数据集的个数,实际使用的时候最好不要用硬编码
		int test_N = 4;   // 测试数据集的个数
		int n_ins = 6;   //  特征的维数
		int n_outs = 2;   // 输出的维数
		int[] hidden_layer_sizes = {10, 9,8,7,6 };  //隐藏层的节点个数
		int n_layers = hidden_layer_sizes.length;  //设置了两个隐藏层
		
		// training data
		int[][] train_X = {
			{1, 1, 1, 0, 0, 0},
			{1, 0, 1, 0, 0, 0},
			{1, 1, 1, 0, 0, 0},
			{0, 0, 1, 1, 1, 0},
			{0, 0, 1, 1, 0, 0},
			{0, 0, 1, 1, 1, 0}
		};
		
		 
		int[][] train_Y = { // 用这样的表示来做二分类， 如果是多维的就是多分类，我他妈真是太聪明了
			{1, 0},
			{1, 0},
			{1, 0},
			{0, 1},
			{0, 1},
			{0, 1},
		};
		
		
		// construct DBN  初始化DBN网络 
		DBN dbn = new DBN(train_N, n_ins, hidden_layer_sizes, n_outs, n_layers, rng);
		
		// pretrain  初始化构造好网络进入pre-traning 阶段， 就是一层一层训练网络 , k=1 是CD 抽样只做一次
		dbn.pretrain(train_X, pretrain_lr, k, pretraining_epochs);
		
		// finetune  在pre-training 构造整个网络之后，用finetune 进行一次微调
		dbn.finetune(train_X, train_Y, finetune_lr, finetune_epochs);
		
		
		// test data
		int[][] test_X = {
			{1, 1, 0, 0, 0, 0},
			{1, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 1, 0},
			{0, 0, 1, 1, 1, 0},
		};
		
		double[][] test_Y = new double[test_N][n_outs];
		
		// test
		for(int i=0; i<test_N; i++) {
			dbn.predict(test_X[i], test_Y[i]); // 对每个输入数据test_x[i]和对应的label 进行预测 ， 值保存在test_Y数组中
			for(int j=0; j<n_outs; j++) {
				System.out.print(test_Y[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		test_dbn();
	}
}



/*import java.util.Random;

public class RBM {
	public int N;
	public int n_visible;
	public int n_hidden;
	public double[][] W;
	public double[] hbias;
	public double[] vbias;
	public Random rng;
	
	public double uniform(double min, double max) {
		return rng.nextDouble() * (max - min) + min;
	}
	
	public int binomial(int n, double p) {
		if(p < 0 || p > 1) return 0;
		
		int c = 0;
		double r;
		
		for(int i=0; i<n; i++) {
			r = rng.nextDouble(); //取一个随机数
			if (r < p) c++;  //这里就是以概率p 来至0或者至1
		}
		
		return c;
	}
	
	public static double sigmoid(double x) {
		return 1.0 / (1.0 + Math.pow(Math.E, -x));
	}
	
	//RBM 的构造函数
	public RBM(int N, int n_visible, int n_hidden, 
			double[][] W, double[] hbias, double[] vbias, Random rng) {
		this.N = N;  // 样本的个数
		this.n_visible = n_visible;  // 可视节点的个数 ， 可视节点的个数就是上一层的输出
		this.n_hidden = n_hidden;   // 隐藏节点的个数 ， 隐藏节点的个数就是这一层的节点个数
	
		if(rng == null)	this.rng = new Random(1234); //获取随机值
		else this.rng = rng;
		
		if(W == null) {  // 初始话 RBM的 W ，  因为在构建的时候，是把隐藏层的 W传过来，所以W不是NULL而是sigmoid_layers[i].W
			this.W = new double[this.n_hidden][this.n_visible];
			double a = 1.0 / this.n_visible;
			
			for(int i=0; i<this.n_hidden; i++) {
				for(int j=0; j<this.n_visible; j++) {
					this.W[i][j] = uniform(-a, a); 
				}
			}	
		} else {
			this.W = W;
		}
		
		if(hbias == null) {  //初始化RBM 的偏差b ， 同理在初始化RBM的时候，hbias是由sigmoid_layers输入的
			this.hbias = new double[this.n_hidden];
			for(int i=0; i<this.n_hidden; i++) this.hbias[i] = 0;
		} else {
			this.hbias = hbias;
		}
		
		if(vbias == null) {   //这里初始化，可视层的偏差， 反正都为0就对了
			this.vbias = new double[this.n_visible];
			for(int i=0; i<this.n_visible; i++) this.vbias[i] = 0;
		} else {
			this.vbias = vbias;
		}
	}
	
	//执行 CD 方法对   w  b  c 进行梯度下降的调整
	public void contrastive_divergence(int[] input, double lr, int k) {
		double[] ph_mean = new double[n_hidden];// n-hidden是这一层的节点个数
		int[] ph_sample = new int[n_hidden]; // 保存的是隐藏层的0-1 值
		double[] nv_means = new double[n_visible];  //采样中 保存的是可视层的 wx+b值
		int[] nv_samples = new int[n_visible];  //采样中 保存的是可视层的0-1值
		double[] nh_means = new double[n_hidden];  // 采样中 隐藏层的 wx+b 值
		int[] nh_samples = new int[n_hidden];  // 采样中 可视层的0-1值
		
		 CD-k 
		sample_h_given_v(input, ph_mean, ph_sample); //ph_mean 是wx+b , ph_sample是归一化到01之间
		
		for(int step=0; step<k; step++) { // k = 1 这里其实也就执行了一次采样
			if(step == 0) { // 执行Gibbs 采样， 从隐藏层到可视层，然后从可视层在返回隐藏层
				gibbs_hvh(ph_sample, nv_means, nv_samples, nh_means, nh_samples);
			} else {  
				gibbs_hvh(nh_samples, nv_means, nv_samples, nh_means, nh_samples);
			}
		}
		
		// 根据梯度下降对 w 的值进行调整 ph_mean为初次计算的隐藏层的值wx+b， input 的原可视层的值（01）， nh_means为采样后的隐藏层的值wx+b， nv为采样后的可视层的值（01）
		for(int i=0; i<n_hidden; i++) {
			for(int j=0; j<n_visible; j++) {
				// W[i][j] += lr *(ph_sample[i] * input[j] - nh_means[i] * nv_samples[j]) / N;
				W[i][j] += lr *(ph_mean[i] * input[j] - nh_means[i] * nv_samples[j]) / N;
			}
			// 对隐藏层的偏差进行调整
			hbias[i] += lr * (ph_sample[i] - nh_means[i]) / N; //ph_sample 为隐藏层的值01 ， nh_means 为采样后返回隐藏层的值wx+b
		}
		
        // 对可视层的偏差进行调整
		for(int i=0; i<n_visible; i++) {
			vbias[i] += lr * (input[i] - nv_samples[i]) / N;
		}

	}
	
	//给定v 计算 h , 其实就是sigmoid(wx+b)
	public void sample_h_given_v(int[] v0_sample, double[] mean, int[] sample) { //mean是wx+b , sample 是归一化到0，1之间
		for(int i=0; i<n_hidden; i++) {  // n-hidden 为这一层的节点个数
			mean[i] = propup(v0_sample, W[i], hbias[i]);  //w[i]为第i个节点的权重， 函数计算这个W*x ， 同时传送对应的偏差
			sample[i] = binomial(1, mean[i]);
		}
	}
   
	//给定 h 计算v 
	public void sample_v_given_h(int[] h0_sample, double[] mean, int[] sample) {
		for(int i=0; i<n_visible; i++) { // n-visible 为可视层的节点个数
			mean[i] = propdown(h0_sample, i, vbias[i]);//h-sample 是从隐藏层传回来的值
			sample[i] = binomial(1, mean[i]);   // sample 保存的值是0-1的归一化结果
		}
	}
	
	//函数计算 sigmoid(wx+b) 
	public double propup(int[] v, double[] w, double b) {
		double pre_sigmoid_activation = 0.0;
		for(int j=0; j<n_visible; j++) { //n-visible 为上一层的输出维度
			pre_sigmoid_activation += w[j] * v[j]; //  整个for循环就是w[i][j]*x[j] ，向量内积
		}
		pre_sigmoid_activation += b; // 对向量内积后的实值加上一个偏差
		return sigmoid(pre_sigmoid_activation); //返回sigmoid 后的数值
	}
	
	//计算从隐藏层返回可视层的wx+b  ??? 为什么不是wc+c
	public double propdown(int[] h, int i, double b) {
	  double pre_sigmoid_activation = 0.0;
	  for(int j=0; j<n_hidden; j++) {
	    pre_sigmoid_activation += W[j][i] * h[j]; // 把隐藏层的结果作为输入， 计算wx+b 
	  }
	  pre_sigmoid_activation += b;
	  return sigmoid(pre_sigmoid_activation);
	}
	
	public void gibbs_hvh(int[] h0_sample, double[] nv_means, int[] nv_samples, double[] nh_means, int[] nh_samples) {
	  sample_v_given_h(h0_sample, nv_means, nv_samples);  // 计算从隐藏层到可视层的结果
	  sample_h_given_v(nv_samples, nh_means, nh_samples);  // 在计算从可视层到隐藏层的结果
	}


	public void reconstruct(int[] v, double[] reconstructed_v) {
	  double[] h = new double[n_hidden];
	  double pre_sigmoid_activation;
	
	  for(int i=0; i<n_hidden; i++) {
	    h[i] = propup(v, W[i], hbias[i]);
	  }
	
	  for(int i=0; i<n_visible; i++) {
	    pre_sigmoid_activation = 0.0;
	    for(int j=0; j<n_hidden; j++) {
	      pre_sigmoid_activation += W[j][i] * h[j];
	    }
	    pre_sigmoid_activation += vbias[i];
	
	    reconstructed_v[i] = sigmoid(pre_sigmoid_activation);
	  }	
	}
}*/




/*import java.util.Random;

public class HiddenLayer {
	public int N;  //样本的个数
	public int n_in;  // 隐藏层的输入维度
	public int n_out;  //隐藏层的输出维度
	public double[][] W;  // 隐藏层的权重
	public double[] b;   // 隐藏层的偏差
	public Random rng;   
	
	public double uniform(double min, double max) {  // 返回  min 到 max的随机值
		return rng.nextDouble() * (max - min) + min;
	}
	
	// 根据概率p , 获取0-1
	public int binomial(int n, double p) {
		if(p < 0 || p > 1) return 0;
		
		int c = 0;
		double r;
		
		for(int i=0; i<n; i++) {
			r = rng.nextDouble();
			if (r < p) c++;
		}
		
		return c;
	}
	
	// sigmoid
	public static double sigmoid(double x) {
		return 1.0 / (1.0 + Math.pow(Math.E, -x));
	}
	
	
	//隐藏层的构建函数
	public HiddenLayer(int N, int n_in, int n_out, double[][] W, double[] b, Random rng) {
		this.N = N;  // 样本数目 
		this.n_in = n_in;   // 这个隐藏层的输入数目
		this.n_out = n_out;   // 这个隐藏层的输出数目 ， 其实就是这一层的节点个数 hidden_layer_size[i]
		
		if(rng == null)	this.rng = new Random(1234);  // 获取随机数
		else this.rng = rng;
	
		if(W == null) { // 这里构建隐藏层的权重， 如果W有值的话，就赋值给他，如果没有值的话就随机初始化
			this.W = new double[n_out][n_in];  //在计算的时候，上一层的输出会和这一层的W进行内积运算
			//W*x , W[n-out][n-in] , x[n-in] ,相乘后是一个 n-out 维的向量，也就是这一层隐藏层的节点个数，作为下一层的输入
			double a = 1.0 / this.n_in;  
			
			for(int i=0; i<n_out; i++) {
				for(int j=0; j<n_in; j++) {
					this.W[i][j] = uniform(-a, a);  //为权值矩阵W赋值 ，随机取一个-a , a 之间的数值
				}
			}
		} else {
			this.W = W;
		}
		
		if(b == null) this.b = new double[n_out];  // 偏差的维度和输出向量的维度应该是要一样的
		else this.b = b;
	}
	
	//这里和RBM 的propup 是一样的
	public double output(int[] input, double[] w, double b) {
		double linear_output = 0.0;
		for(int j=0; j<n_in; j++) {
			linear_output += w[j] * input[j];
		}
		linear_output += b;
		return sigmoid(linear_output);
	}
	
	// 这里和RBM 的wx+b 是一样的 , 输入input是维度为n-in 的向量，输出sample是n-out的向量
	public void sample_h_given_v(int[] input, int[] sample) {
		for(int i=0; i<n_out; i++) {  // n-out就是层有多少个节点，也就是这层的输出维度
			sample[i] = binomial(1, output(input, W[i], b[i])); //输入input是上一层的输出,W[i]是这层W的第i行, 也就是这层
		}
	}
}




public class LogisticRegression {
	public int N;
	public int n_in;
	public int n_out;
	public double[][] W;
	public double[] b;
	
	//逻辑回归的构建函数 
	public LogisticRegression(int N, int n_in, int n_out) {
		this.N = N;// N 为样本的个数
		this.n_in = n_in; // i_in 为输入的维度数，是hidden_layer最后一层的输出数
		this.n_out = n_out;  // n-out 为之前设定的DBN的输出维数
		
		W = new double[this.n_out][this.n_in];// [n-out][n-in]* [n-in] = [n-out]
		b = new double[this.n_out];
	}
	
	// 输入数据是x , label 是y , lr是学习率 ， 一次计算一个样本
	public void train(int[] x, int[] y, double lr) {
		double[] p_y_given_x = new double[n_out]; //根据最后输出数据的维度声明数组
		double[] dy = new double[n_out];
		
		for(int i=0; i<n_out; i++) { //计算不同维度上的概率值
			p_y_given_x[i] = 0;
			for(int j=0; j<n_in; j++) { //计算逻辑回归的 wx+b的
				p_y_given_x[i] += W[i][j] * x[j];
			}
			p_y_given_x[i] += b[i]; //加上偏差
		}
		softmax(p_y_given_x); //然后对这个估计值做softmax
		
		for(int i=0; i<n_out; i++) {
			dy[i] = y[i] - p_y_given_x[i];  //计算实际label
			
			for(int j=0; j<n_in; j++) {
				W[i][j] += lr * dy[i] * x[j] / N;  // 根据偏差做梯度下降修改W , 逻辑回归的W初始的时候是0
			}
			
			b[i] += lr * dy[i] / N;  // 同时修改 偏差
		}
	}
	
	public void softmax(double[] x) {
		double max = 0.0;
		double sum = 0.0;
		
		for(int i=0; i<n_out; i++) { // x 的维度为 n_out , 找到最大的值
			if(max < x[i]) {
				max = x[i];
			}
		}
		
		for(int i=0; i<n_out; i++) {
			x[i] = Math.exp(x[i] - max);
			sum += x[i];
		}
		
		for(int i=0; i<n_out; i++) { //归一化
			x[i] /= sum;
		}
	}
	
	public void predict(int[] x, double[] y) {
		for(int i=0; i<n_out; i++) {
			y[i] = 0;
			for(int j=0; j<n_in; j++) {
				y[i] += W[i][j] * x[j];
			}
			y[i] += b[i];
		}
		
		softmax(y);
	}		*/
