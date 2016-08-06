/*package com.fp.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fp.domain.Cluster;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class SVD {
   public static void main(String[] args) throws Exception { 

	   //List<Cluster> list=svd();
      // create M-by-N matrix that doesn't have full rank
      int M = 8, N = 5;
      Matrix B = Matrix.random(5, 3);
      Matrix A = Matrix.random(M, N).times(B).times(B.transpose());
      WordSplit wordSplit=new WordSplit();
      double[][] matrix=wordSplit.wordTFMatriex();
      Matrix C=Matrix.constructWithCopy(matrix);
      // Matrix A = Matrix.random(M, N);
      //System.out.print("C = ");
      //C.print(1,6);
      //System.out.println(matrix[0].length);
      // compute the singular vallue decomposition
      System.out.println("C = U S V^T");
      System.out.println("开始奇异值分解~");
      SingularValueDecomposition s = C.svd();
      System.out.print("U = ");
      Matrix U = s.getU();
     
      U.print(1, 6);
      System.out.print("Sigma = ");
     // Matrix S = s.getS();
     // S.print(1, 6);
      System.out.print("V = ");
      Matrix V = s.getV();
      V.print(9, 6);
      System.out.println("rank = " + s.rank());
      System.out.println("condition number = " + s.cond());
      System.out.println("2-norm = " + s.norm2());

      // print out singular values
      System.out.print("singular values = ");
      Matrix svalues = new Matrix(s.getSingularValues(), 1);
      svalues.print(1, 6);
      double[][] matrix2=V.getArray();
   }
   //SVD分解降维，得到三维数据。
   public  List<Cluster> svd() throws Exception{
	// create M-by-N matrix that doesn't have full rank
	      List<Cluster> clusters=new ArrayList<>();
	      int M = 8, N = 5;
	      Matrix B = Matrix.random(5, 3);
	      Matrix A = Matrix.random(M, N).times(B).times(B.transpose());
	      WordSplit wordSplit=new WordSplit();
	      ArrayList list=wordSplit.wordTFMatriex();
	      double[][] matrix=(double[][]) list.get(0);
	      Map<String, HashSet<String>> map5=(Map<String, HashSet<String>>) list.get(1);
	      ArrayList listStr=new ArrayList<>();
	      for (String str:map5.keySet()) {
			listStr.add(str);
		 }
	      System.out.println(listStr.size());
	      Matrix C=Matrix.constructWithCopy(matrix);
	      // Matrix A = Matrix.random(M, N);
	      //System.out.print("C = ");
	      //C.print(1,6);

	      // compute the singular vallue decomposition
	      System.out.println("C = U S V^T");
	      System.out.println("开始奇异值分解~");
	      SingularValueDecomposition s = C.svd();
	      System.out.print("U = ");
	      Matrix U = s.getU();
	      //double[][] matrixword=U.getArray();
	      //U.print(1, 6);
	      System.out.print("Sigma = ");
	      Matrix S = s.getS();
	     // S.print(1, 6);
	      System.out.print("V = ");
	      Matrix V = s.getV();
	      //V.print(9, 6);
	      System.out.println("rank = " + s.rank());
	      System.out.println("condition number = " + s.cond());
	      System.out.println("2-norm = " + s.norm2());

	      // print out singular values
	      System.out.print("singular values = ");
	      Matrix svalues = new Matrix(s.getSingularValues(), 1);
	      //svalues.print(1, 6);
	      //double[][] matrixdoc=V.getArray();
	      double[][] matrixword=U.getArray();
	      for (int i = 0; i < matrixword.length; i++) {
				Cluster cluster=new Cluster();
				cluster.setName(listStr.get(i).toString());
				cluster.setX(matrixword[i][0]);
				cluster.setY(matrixword[i][1]);
				cluster.setZ(matrixword[i][2]);
				clusters.add(cluster);
		}
	      //System.out.println(clusters.size());
	      return clusters;
   }
   
}*/