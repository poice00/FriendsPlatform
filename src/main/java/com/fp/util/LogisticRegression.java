package com.fp.util;

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
	}
}