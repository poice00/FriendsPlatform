package com.fp.util;

import java.util.Random;

public class HiddenLayer {
	public int N;  //样本的个数
	public int n_in;  // 隐藏层的输入维度
	public int n_out;  //隐藏层的输出维度
	public double[][] W;  // 隐藏层的权重
	public double[] b;   // 隐藏层的偏差
	public Random rng;   //随机数函数
	
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




