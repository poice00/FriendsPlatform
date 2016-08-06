package com.fp.util;

import java.util.Random;

public class RBM {
	public int N;
	public int n_visible;
	public int n_hidden;
	public double[][] W;
	public double[] hbias;
	public double[] vbias;
	public Random rng;
	
	public double uniform(double min, double max) {// 返回  min 到 max的随机值
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
}