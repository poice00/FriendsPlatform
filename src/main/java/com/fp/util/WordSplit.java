package com.fp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Map.Entry;

import org.junit.Test;

import com.fp.domain.BbsTopic;
import com.fp.domain.Topic;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;


public class WordSplit {
	
	@SuppressWarnings({ "unchecked", "hiding", "resource" })
	public static <Term> void main(String[] args) throws Exception {
		BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(
				new File("E:\\workspace\\FriendsPlatform\\src\\main\\webapp\\data\\dictionary\\stopwords.txt"))));
		HashSet<String> stopWordSet = new HashSet<String>();
		String stopWord = null;
		for (; (stopWord = StopWordFileBr.readLine()) != null;) {
			stopWordSet.add(stopWord);
		}
		BbsDataGetService bbsDataGetService=new BbsDataGetService();
		List<BbsTopic> topics=bbsDataGetService.selectBbsTopicData();
		String textToSeg="";		
		
		Map<String, Integer> map = new TreeMap<String, Integer>(); //统计每个词出现的次数 		
		
		Map<String, Integer> map2 = new TreeMap<String, Integer>(); 	//统计每个文档的总词语数
		//定义嵌套map函数，
		Map<String, Map<String, Integer>> map4=new TreeMap<String, Map<String,Integer>>(); 	//String为文档ID，其中的MAP为文档的词及词频数目	
		//倒排序文档集，统计每个词出现的文档集合
		Map<String, HashSet<String>> map5=new TreeMap<String, HashSet<String>>(); 
		
		for (int i = 0; i < topics.size(); i++) {
			textToSeg=	topics.get(i).getContent().replaceAll("[^\u4E00-\u9FA5]", "");
			if (textToSeg.length()>=10) {
				HanLP.Config.ShowTermNature=false;
				SpeedTokenizer.SEGMENT.enableCustomDictionary(true);
				CustomDictionary.add("太太");
				CustomDictionary.add("约会");
				CustomDictionary.add("龙龟");
				List<Term> words=(List<Term>) SpeedTokenizer.segment(textToSeg);
				Map<String, Integer> map3 = new TreeMap<String, Integer>(); //每个文档自身的词及词频
				int topicTotalWords=words.size();
				for (int j = 0; j < topicTotalWords; j++) { 
		        	//System.out.println(words.get(i).toString());
					
		        	if (stopWordSet.contains(words.get(j).toString())) {
		        		topicTotalWords=topicTotalWords-1;
					} else {
						if (map3.containsKey(words.get(j).toString())) {
							int tempCount = map3.get(words.get(j).toString());  
			                map3.put(words.get(j).toString(), ++tempCount); 	
						} else {
							map3.put(words.get(j).toString(), 1); 
						}
						if (map.containsKey(words.get(j).toString())) {  
			                int tempCount = map.get(words.get(j).toString());  
			                map.put(words.get(j).toString(), ++tempCount);  
			            } else {  
			                map.put(words.get(j).toString(), 1);  
			            }
						if (map5.containsKey(words.get(j).toString())) {
							HashSet<String> docmentset = map5.get(words.get(j).toString());
							docmentset.add(topics.get(i).getId().toString());
			                map5.put(words.get(j).toString(), docmentset); 
						} else {
							HashSet<String> docmentset = new HashSet<String>();
							docmentset.add(topics.get(i).getId().toString());
							map5.put(words.get(j).toString(), docmentset);
						}
					}   
		        }
				map2.put(topics.get(i).getId().toString(), topicTotalWords);
				map4.put(topics.get(i).getId().toString(), map3);
			} 
		}	
		//定义嵌套map函数，
		//String为文档ID，其中的MAP为所有的词及tf*idf权重
		Map<String, Map<String, Float>> map6=new TreeMap<String, Map<String, Float>>(); 
		float total=map4.size();
		System.out.println(map4.size());
		System.out.println(map4.keySet().size());
		for (String documentId:map4.keySet()) {
			Map<String, Float> map3 = new TreeMap<String, Float>(); //每个文档的tf及idf计算
			for (String wordId:map5.keySet()) {
				if (map4.get(documentId).containsKey(wordId)) {
					float tf=map4.get(documentId).get(wordId).floatValue()/map2.get(documentId).floatValue();
					float idf=(float) Math.log(total/((float)map5.get(wordId).size()+1.0));
					float weight=tf*idf;
					map3.put(wordId, weight);
				} else {
					map3.put(wordId, (float) 0);
				}
			}
			System.out.println(map3);
			map6.put(documentId, map3);
		}
		
		
		/*for (int i = 0; i < resultArray.length; i++) {
			if (stopWordSet.contains(resultArray[i])) {
				resultArray[i] = null;
			}
		}*/
		/*Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        List<Term> parse2 = (List<Term>) HanLP.segment(testText);		
	    System.out.println(parse2);  
	    Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        List<Term> parse3 = (List<Term>) shortestSegment.seg(testText);		
	    System.out.println(parse3);
	    List<Term> termList2 = (List<Term>) NLPTokenizer.segment("上海交大学生");
	    System.out.println(termList2);*/
	    /*KeyWordComputer kwc = new KeyWordComputer(5);
	    Collection<Keyword> result = kwc.computeArticleTfidf("", testText);
        System.out.println(result);*/
        /*List<Term> words=(List<Term>) IndexTokenizer.segment(testText);
        System.out.println(words);*/
       /* List<String> sentenceList = HanLP.extractSummary(testText, 3);
		System.out.println(sentenceList);*/
        /*for (String str:map.keySet()) {
			System.out.println(str+"|"+map.get(str));
		}*/
	}
	
	@Test
	public void TestWordSeg() throws Exception{
		  String testText="这个电视机还是挺不错的呢";
		 List<Term> parse = (List<Term>) SpeedTokenizer.segment(testText);
		 System.out.println(parse);
	}
    //tf*idf矩阵
	public double[][] wordTFIDFMatriex() throws Exception{
		
		BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(
				new File("E:\\workspace\\FriendsPlatform\\src\\main\\webapp\\data\\dictionary\\stopwords.txt"))));
		HashSet<String> stopWordSet = new HashSet<String>();
		String stopWord = null;
		for (; (stopWord = StopWordFileBr.readLine()) != null;) {
			stopWordSet.add(stopWord);
		}
		BbsDataGetService bbsDataGetService=new BbsDataGetService();
		List<BbsTopic> topics=bbsDataGetService.selectBbsTopicData();
		String textToSeg="";		
		//统计每个词出现的次数 
		Map<String, Integer> map = new TreeMap<String, Integer>(); 		
		//统计每个文档的总词语数
		Map<String, Integer> map2 = new TreeMap<String, Integer>(); 	
		//定义嵌套map函数，
		//String为文档ID，其中的MAP为文档的词及词频数目
		Map<String, Map<String, Integer>> map4=new TreeMap<String, Map<String,Integer>>(); 		
		//倒排序文档集，统计每个词出现的文档集合
		Map<String, HashSet<String>> map5=new TreeMap<String, HashSet<String>>(); 
		
		for (int i = 0; i < topics.size(); i++) {
			textToSeg=	topics.get(i).getContent().replaceAll("[^\u4E00-\u9FA5]", "");
			if (textToSeg.length()>=10) {
				HanLP.Config.ShowTermNature=false;
				List<Term> words=(List<Term>) SpeedTokenizer.segment(textToSeg);
				Map<String, Integer> map3 = new TreeMap<String, Integer>(); //每个文档自身的词及词频
				int topicTotalWords=words.size();
				for (int j = 0; j < topicTotalWords; j++) { 
		        	//System.out.println(words.get(i).toString());
					
		        	if (stopWordSet.contains(words.get(j).toString())) {
		        		topicTotalWords=topicTotalWords-1;
					} else {
						if (map3.containsKey(words.get(j).toString())) {
							int tempCount = map3.get(words.get(j).toString());  
			                map3.put(words.get(j).toString(), ++tempCount); 	
						} else {
							map3.put(words.get(j).toString(), 1); 
						}
						if (map.containsKey(words.get(j).toString())) {  
			                int tempCount = map.get(words.get(j).toString());  
			                map.put(words.get(j).toString(), ++tempCount);  
			            } else {  
			                map.put(words.get(j).toString(), 1);  
			            }
						if (map5.containsKey(words.get(j).toString())) {
							HashSet<String> docmentset = map5.get(words.get(j).toString());
							docmentset.add(topics.get(i).getId().toString());
			                map5.put(words.get(j).toString(), docmentset); 
						} else {
							HashSet<String> docmentset = new HashSet<String>();
							docmentset.add(topics.get(i).getId().toString());
							map5.put(words.get(j).toString(), docmentset);
						}
					}   
		        }
				map2.put(topics.get(i).getId().toString(), topicTotalWords);
				map4.put(topics.get(i).getId().toString(), map3);
			} 
		}	
		//定义嵌套map函数，
		//String为文档ID，其中的MAP为所有的词及tf*idf权重
		Map<String, Map<String, Float>> map6=new TreeMap<String, Map<String, Float>>(); 
		float total=map4.size();
		for (String documentId:map4.keySet()) {
			Map<String, Float> map3 = new TreeMap<String, Float>(); //每个文档的tf及idf计算
			for (String wordId:map5.keySet()) {
				if (map4.get(documentId).containsKey(wordId)) {
					float tf=map4.get(documentId).get(wordId).floatValue()/map2.get(documentId).floatValue();
					float idf=(float) Math.log(total/((float)map5.get(wordId).size()+1.0));
					float weight=tf*idf;
					map3.put(wordId, weight);
				} else {
					map3.put(wordId, (float) 0);
				}
			}
			map6.put(documentId, map3);
		}
		double[][] d=new double[map5.size()][map4.size()];
		int n=0;
		for (String string:map6.keySet()) {
			int m=0;
			for (String string2:map6.get(string).keySet()) {
				d[m][n]=map6.get(string).get(string2).doubleValue();
				m=m+1;		
			}
			n=n+1;
		}
		return d;
	}
	
	//词语-文档次数矩阵
		public ArrayList wordTFMatriex() throws Exception{
			
			BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File("E:\\workspace\\FriendsPlatform\\src\\main\\webapp\\data\\dictionary\\stopwords.txt"))));
			HashSet<String> stopWordSet = new HashSet<String>();
			String stopWord = null;
			for (; (stopWord = StopWordFileBr.readLine()) != null;) {
				stopWordSet.add(stopWord);
			}
			BbsDataGetService bbsDataGetService=new BbsDataGetService();
			List<BbsTopic> topics=bbsDataGetService.selectBbsTopicData();
			String textToSeg="";		
			//统计每个词出现的次数 
			Map<String, Integer> map = new TreeMap<String, Integer>(); 		
			//统计每个文档的总词语数
			Map<String, Integer> map2 = new TreeMap<String, Integer>(); 	
			//定义嵌套map函数，
			//String为文档ID，其中的MAP为文档的词及词频数目
			Map<String, Map<String, Integer>> map4=new TreeMap<String, Map<String,Integer>>(); 		
			//倒排序文档集，统计每个词出现的文档集合
			Map<String, HashSet<String>> map5=new TreeMap<String, HashSet<String>>(); 
			
			for (int i = 0; i < topics.size(); i++) {
				textToSeg=	topics.get(i).getContent().replaceAll("[^\u4E00-\u9FA5]", "");
				if (textToSeg.length()>=10) {
					HanLP.Config.ShowTermNature=false;
					List<Term> words=(List<Term>) SpeedTokenizer.segment(textToSeg);
					Map<String, Integer> map3 = new TreeMap<String, Integer>(); //每个文档自身的词及词频
					int topicTotalWords=words.size();
					for (int j = 0; j < topicTotalWords; j++) { 
			        	//System.out.println(words.get(i).toString());
						
			        	if (stopWordSet.contains(words.get(j).toString())) {
			        		topicTotalWords=topicTotalWords-1;
						} else {
							if (map3.containsKey(words.get(j).toString())) {
								int tempCount = map3.get(words.get(j).toString());  
				                map3.put(words.get(j).toString(), ++tempCount); 	
							} else {
								map3.put(words.get(j).toString(), 1); 
							}
							if (map.containsKey(words.get(j).toString())) {  
				                int tempCount = map.get(words.get(j).toString());  
				                map.put(words.get(j).toString(), ++tempCount);  
				            } else {  
				                map.put(words.get(j).toString(), 1);  
				            }
							if (map5.containsKey(words.get(j).toString())) {
								HashSet<String> docmentset = map5.get(words.get(j).toString());
								docmentset.add(topics.get(i).getId().toString());
				                map5.put(words.get(j).toString(), docmentset); 
							} else {
								HashSet<String> docmentset = new HashSet<String>();
								docmentset.add(topics.get(i).getId().toString());
								map5.put(words.get(j).toString(), docmentset);
							}
						}   
			        }
					map2.put(topics.get(i).getId().toString(), topicTotalWords);
					map4.put(topics.get(i).getId().toString(), map3);
				} 
			}	
			//定义嵌套map函数，
			//String为文档ID，其中的MAP为所有的词及词在文档中的频率

			float total=map4.size();
			double[][] d=new double[map5.size()][map4.size()];
			int n=0;
			for (String douId:map4.keySet()) {
				int m=0;
				for (String word:map5.keySet()) {
					if (map4.get(douId).containsKey(word)) {
						d[m][n]=map4.get(douId).get(word);
					} else {
						d[m][n]=0;
					}
					m=m+1;		
				}
				n=n+1;
			}
			ArrayList list=new ArrayList<>();
			list.add(d);
			list.add(map5);
			return list;
		}
	
	/*生成文档向量模型*/
	public ArrayList computeTFMultiIDF() throws Exception{
		BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(new FileInputStream(
				new File("E:\\workspace\\FriendsPlatform\\src\\main\\webapp\\data\\dictionary\\stopwords.txt"))));
		HashSet<String> stopWordSet = new HashSet<String>();
		String stopWord = null;
		for (; (stopWord = StopWordFileBr.readLine()) != null;) {
			stopWordSet.add(stopWord);
		}
		BbsDataGetService bbsDataGetService=new BbsDataGetService();
		List<Topic> topics=bbsDataGetService.selectTopicData();
		String textToSeg="";		
		//统计每个词出现的次数 
		Map<String, Integer> map = new TreeMap<String, Integer>(); 		
		//统计每个文档的总词语数
		Map<String, Integer> map2 = new TreeMap<String, Integer>(); 	
		//定义嵌套map函数，
		//String为文档ID，其中的MAP为文档的词及词频数目
		Map<String, Map<String, Integer>> map4=new TreeMap<String, Map<String,Integer>>(); 		
		//倒排序文档集，统计每个词出现的文档集合
		Map<String, HashSet<String>> map5=new TreeMap<String, HashSet<String>>(); 
		
		for (int i = 0; i < topics.size(); i++) {
			textToSeg=	topics.get(i).getContent().replaceAll("[^\u4E00-\u9FA5]", "");
			if (textToSeg.length()>=10) {
				HanLP.Config.ShowTermNature=false;
				SpeedTokenizer.SEGMENT.enableCustomDictionary(true);
				CustomDictionary.add("太太");
				CustomDictionary.add("约会");
				CustomDictionary.add("龙龟");
				List<Term> words=(List<Term>) SpeedTokenizer.segment(textToSeg);
				Map<String, Integer> map3 = new TreeMap<String, Integer>(); //每个文档自身的词及词频
				int topicTotalWords=words.size();
				for (int j = 0; j < topicTotalWords; j++) { 
		        	//System.out.println(words.get(i).toString());
		        	if (stopWordSet.contains(words.get(j).toString())) {
		        		topicTotalWords=topicTotalWords-1;
					} else {
						if (map3.containsKey(words.get(j).toString())) {
							int tempCount = map3.get(words.get(j).toString());  
			                map3.put(words.get(j).toString(), ++tempCount); 	
						} else {
							map3.put(words.get(j).toString(), 1); 
						}
						if (map.containsKey(words.get(j).toString())) {  
			                int tempCount = map.get(words.get(j).toString());  
			                map.put(words.get(j).toString(), ++tempCount);  
			            } else {  
			                map.put(words.get(j).toString(), 1);  
			            }
						if (map5.containsKey(words.get(j).toString())) {
							HashSet<String> docmentset = map5.get(words.get(j).toString());
							docmentset.add(topics.get(i).getId().toString());
			                map5.put(words.get(j).toString(), docmentset); 
						} else {
							HashSet<String> docmentset = new HashSet<String>();
							docmentset.add(topics.get(i).getId().toString());
							map5.put(words.get(j).toString(), docmentset);
						}
					}   
		        }
				map2.put(topics.get(i).getId().toString(), topicTotalWords);
				map4.put(topics.get(i).getId().toString(), map3);
			} 
		}	
		//定义嵌套map函数，
		//String为文档ID，其中的MAP为所有的词及tf*idf权重
		Map<String, Map<String, Double>> map6=new TreeMap<String, Map<String, Double>>(); 
		float total=map4.size();
		for (String documentId:map4.keySet()) {
			Map<String, Double> map3 = new TreeMap<String, Double>(); //每个文档的tf及idf计算
			for (String wordId:map5.keySet()) {
				if (map4.get(documentId).containsKey(wordId)) {
					float tf=map4.get(documentId).get(wordId).floatValue()/map2.get(documentId).floatValue();
					float idf=(float) Math.log(total/((float)map5.get(wordId).size()+1.0));
					float weight=tf*idf;
					map3.put(wordId, (double) weight);
				} else {
					map3.put(wordId, 0.0);
				}
			}
			map6.put(documentId, map3);
		}
		ArrayList listMap=new ArrayList<>();
		listMap.add(map4);
		listMap.add(map5);
		listMap.add(map6);
		listMap.add(map2);
		return listMap;
	}
	
	/*聚类结果评估函数*/
	/**评估函数根据聚类结果文件统计熵和混淆矩阵 
     * @param clusterResultFile 聚类结果文件 
     * @param K 聚类数目 
     * @return double 聚类结果的熵值 
     * @throws IOException  
     */  
    public double evaluateClusterRes(String clusterResultFile, int K) throws Exception {  
        // TODO Auto-generated method stub  
        Map<String,String> rightCate = new TreeMap<String,String>();  
        Map<String,String> resultCate = new TreeMap<String,String>();  
        FileReader crReader = new FileReader(clusterResultFile);  
        BufferedReader crBR = new BufferedReader(crReader);  
        String[] s;  
        String line;  
        while((line = crBR.readLine()) != null){  
            s = line.split(" ");  
            resultCate.put(s[0], s[1]);   
            //再把s[0]用_分片  
            rightCate.put(s[0], s[0].split("_")[0]);  
        }  
        return computeEntropyAndConfuMatrix(rightCate,resultCate,K);//返回熵  
    }  
      
    /**计算混淆矩阵并且输出，返回熵 
     * @param rightCate 正确类目对应map 
     * @param resultCate 聚类结果对应map 
     * @return double 返回聚类的熵 
     * @throws IOException  
     */  
    public double computeEntropyAndConfuMatrix(Map<String, String> rightCate,  
            Map<String, String> resultCate, int K) throws Exception{  
        // TODO Auto-generated method stub    
        int[][] confusionMatrix = new int[K][20];//K行20列，[i,j]表示聚类i中属于类目j的文件数  
        //首先求出类目对应的数组索引  
        SortedSet<String> cateNames = new TreeSet<String>();  
        Set<Map.Entry<String, String>> rightCateSet = rightCate.entrySet();  
        for(Iterator<Map.Entry<String, String>> it = rightCateSet.iterator(); it.hasNext();){  
            Map.Entry<String, String> me = it.next();  
            cateNames.add(me.getValue());  
        }  
        String[] cateNamesArray = cateNames.toArray(new String[0]);  
        Map<String,Integer> cateNamesToIndex = new TreeMap<String,Integer>();  
        for(int i = 0; i < cateNamesArray.length; i++){  
            cateNamesToIndex.put(cateNamesArray[i],i);  
        }  
        for(Iterator<Map.Entry<String, String>> it = rightCateSet.iterator(); it.hasNext();){  
            Map.Entry<String, String> me = it.next();  
            confusionMatrix[Integer.parseInt(resultCate.get(me.getKey()))][cateNamesToIndex.get(me.getValue())]++;  
        }  
        //输出混淆矩阵  
        double [] clusterSum = new double[K];//记录每个聚类的文件数  
        double[] everyClusterEntropy = new double[K];//记录每个聚类的熵  
        double clusterEntropy = 0;  
        System.out.print("    ");  
        for(int i = 0; i < 20; i++){  
            System.out.print(i + "    ");  
        }  
        System.out.println();  
        for(int i = 0; i < K; i++){  
            System.out.print(i + "    ");  
            for(int j = 0; j < 20; j++){  
                clusterSum[i] += confusionMatrix[i][j];  
                System.out.print(confusionMatrix[i][j]+"    ");  
            }  
            System.out.println();  
        }  
        System.out.println();  
        for(int i = 0; i < K; i++){  
            if(clusterSum[i] != 0){  
                for(int j = 0; j < 20; j++){  
                     double p = (double)confusionMatrix[i][j]/clusterSum[i];  
                     if(p != 0){  
                         everyClusterEntropy[i] += -p * Math.log(p);  
                     }  
                }  
                clusterEntropy += clusterSum[i]/(double)rightCate.size() * everyClusterEntropy[i];  
            }  
        }  
        return clusterEntropy;  
    }
   //新的评估函数，适应于用余弦相似度进行计算的情况
	public double evaluateClusterResult(Map<Integer, HashSet<String>> kmeansClusterResult2,Map<String, Map<String, Double>> allTestSampleMap) throws Exception{
		// TODO Auto-generated method stub
		Map<Integer, Map<String, Double>>  MeanMapAll = computeNewMean(kmeansClusterResult2,allTestSampleMap);
		double totalSim=0;
		for (Integer mm:MeanMapAll.keySet()) {//取每一个类
			Map<String, Double> currentMeanMap=MeanMapAll.get(mm); //当前的类中心
			for (String docstr:kmeansClusterResult2.get(mm)) {
				double nn=getDistance(currentMeanMap, allTestSampleMap.get(docstr));
				totalSim+=nn;
			}
		}
		return totalSim;
	}  
	/**计算两个点的余弦相似度距离 
     * @param map1 点1的向量map 
     * @param map2 点2的向量map 
     * @return double 两个点的距离 ,
     */  
    private double getDistance(Map<String, Double> map1, Map<String, Double> map2) {  
        // TODO Auto-generated method stub  
        return computeSim(map1,map2);  
    } 
    /**计算两个文本的相似度 ，使用余弦相似度的方法计算
     * @param testWordTFMap 文本1的<单词,词频>向量 ，在此使用的是单词，权重，即tf*idf
     * @param trainWordTFMap 文本2<单词,词频>向量  ，在此使用的是单词，权重，即tf*idf
     * @return Double 向量之间的相似度 以向量夹角余弦计算或者向量内积计算（效果相当而速度更快） 
     * @throws IOException  
     */  
    private double computeSim(Map<String, Double> testWordTFMap,  
            Map<String, Double> trainWordTFMap) {  
        // TODO Auto-generated method stub  
        double mul = 0;//, testAbs = 0, trainAbs = 0;
        double xAbs = 0, yAbs = 0;
        Set<Map.Entry<String, Double>> testWordTFMapSet = testWordTFMap.entrySet();  
        for(Iterator<Map.Entry<String, Double>> it = testWordTFMapSet.iterator(); it.hasNext();){  
            Map.Entry<String, Double> me = it.next();  
            if(trainWordTFMap.containsKey(me.getKey())){  
                mul += me.getValue()*trainWordTFMap.get(me.getKey());  
            }  
            xAbs += me.getValue() * me.getValue(); 
            yAbs += trainWordTFMap.get(me.getKey()) * trainWordTFMap.get(me.getKey());
        } 
        xAbs = Math.sqrt(xAbs);
        yAbs = Math.sqrt(yAbs);
        //testAbs = Math.sqrt(testAbs);  
          
        /*Set<Map.Entry<String, Double>> trainWordTFMapSet = trainWordTFMap.entrySet(); 
        for(Iterator<Map.Entry<String, Double>> it = trainWordTFMapSet.iterator(); it.hasNext();){ 
            Map.Entry<String, Double> me = it.next(); 
            trainAbs += me.getValue()*me.getValue(); 
        } 
        trainAbs = Math.sqrt(trainAbs);*/  
        return mul/( xAbs * yAbs);/// (testAbs * trainAbs);  
    }
	/**计算当前聚类的中心，采用向量平均 
     * @param clusterM 类序号，及类序号中的文档编号
     * @param allTestSampleMap 所有测试样例的<文件名，向量>构成的map 
     * @param testSampleNames 所有测试样例文件名构成的数组 
     * @return Map<String, Double> 新的聚类中心的向量 
     * @throws IOException  
     */  
    private Map<Integer, Map<String, Double>> computeNewMean(Map<Integer, HashSet<String>> kmeansClusterResult,Map<String, Map<String, Double>> allTestSampleMap) {  
        // TODO Auto-generated method stub  
        double memberNum = (double)kmeansClusterResult.size();  
        Map<String, Double> newMeanMap = new TreeMap<String,Double>();  //新的聚类中心点 
        Map<Integer, Map<String, Double>>  newMeanMapAll = new TreeMap<Integer, Map<String, Double>>();  //所有的聚类中心点 
        Map<String, Double> currentMemMap = new TreeMap<String,Double>(); //存储当前的数值

        for (Integer mm:kmeansClusterResult.keySet()) {
			for (String str:kmeansClusterResult.get(mm)) {
				currentMemMap=allTestSampleMap.get(str);
				for (String cruStr:currentMemMap.keySet()) {
					if(newMeanMap.containsKey(cruStr)){  //如果新的包含，则值相加
	                    newMeanMap.put(cruStr, newMeanMap.get(cruStr) + currentMemMap.get(cruStr));
	                }   
	                else {  //如果新的不包含，则值不相加，即对于第一次迭代时
	                    newMeanMap.put(cruStr, currentMemMap.get(cruStr));  
	                }
				}  
			}
			Set<Map.Entry<String, Double>> newMeanMapSet = newMeanMap.entrySet();  //更新新的聚类中心点，即把向量中心点平均
            for(Iterator<Map.Entry<String, Double>> jt = newMeanMapSet.iterator(); jt.hasNext();){  
                Map.Entry<String, Double> ne = jt.next();  
                newMeanMap.put(ne.getKey(), newMeanMap.get(ne.getKey()) / memberNum);     
            }
            newMeanMapAll.put(mm, newMeanMap);
		}
        return newMeanMapAll;  
    }
    /*计算蔟类中权重最大的words*/
	public ArrayList computeTopWords(Map<Integer, HashSet<String>> kmeansClusterResultLast, int i, Map<String, Map<String, Integer>> map4, Map<String, HashSet<String>> map5, Map<String, Integer> map2) throws Exception{
		// TODO Auto-generated method stub
		//定义嵌套map函数，
		//String为文档ID，其中的MAP为文档的词及词频数目
		ArrayList list=new ArrayList<>();
		Map<String, Integer> tempMap=new TreeMap<>(); //中间词频表
		Map<String, HashSet<String>> tempmap2=new TreeMap<>(); //中间词-文档表
		//定义嵌套map函数，
		// String为文档ID，其中的MAP为所有的词及tf*idf权重
		Map<String, Map<String, Double>> map6 = new TreeMap<String, Map<String, Double>>();
		for (Integer clusterNum:kmeansClusterResultLast.keySet()) {
			int totalWordTemp=0;
			Map<String, Float> map3 = new TreeMap<String, Float>(); // 每个蔟的tf及idf计算
			for (String douId:kmeansClusterResultLast.get(clusterNum)) { //得到每个蔟 词-词频的中间MAP，词-文档的中间MAP
				for (String word:map4.get(douId).keySet()) {
					totalWordTemp=totalWordTemp+map4.get(douId).get(word);
					if (tempMap.containsKey(word)) {
						tempMap.put(word, tempMap.get(word)+map4.get(douId).get(word));
					} else {
						tempMap.put(word, map4.get(douId).get(word));
					}
					if (tempmap2.containsKey(word)) {
						if (tempmap2.get(word).contains(douId)) {
							
						}else
						{
							HashSet<String> set=tempmap2.get(word);
							set.add(douId);
							tempmap2.put(word, set);
						}
						
					} else {
						HashSet<String> docmentset = new HashSet<String>();
						docmentset.add(douId);
						tempmap2.put(word, docmentset);
					}
				}
			}
			System.out.println(tempMap);
			System.out.println(tempmap2);
			//计算每个词的权重
			for (String word:tempMap.keySet()) {
				float tf= tempMap.get(word).floatValue()/totalWordTemp;
				float idf=(float) Math.log(kmeansClusterResultLast.get(clusterNum).size() / ((float) tempmap2.get(word).size() + 1.0));
				float weight = tf * idf;
				map3.put(word, weight);
			}
			//对map3进行排序
			System.out.println(map3);
			 Map<String,Float> newMap = new LinkedHashMap();
			 newMap=sortMap(map3);
			 Map<String,Float> newMap2 = new LinkedHashMap();
			 int wordCount=i/2;
			 for (String word:newMap.keySet()) {
				if (wordCount>0) {		
					newMap2.put(word, newMap.get(word));
					wordCount=wordCount-1;
				}
			 }
			 System.out.println(newMap2.size());
			 list.add(newMap2);
		}
		return list;
	}  
	
	//将list的内容写入json
	public void writeToJson(String path,List list) throws Exception {
		Map<String, Float> map=(Map<String, Float>) list.get(0);
		Map<String, Float> map2=(Map<String, Float>) list.get(1);
		/*float mapmin=1;
		float mapmax=0;
		float map2min=1;
		float map2max=0;
		for (String word:map.keySet()) {
			if (map.get(word)>mapmax) {
				mapmax=map.get(word);
			} 
			if (map.get(word)<mapmin) {
				mapmin=map.get(word);
			} 
		}
		for (String word:map2.keySet()) {
			if (map2.get(word)>map2max) {
				mapmax=map.get(word);
			} 
			if (map.get(word)<mapmin) {
				mapmin=map.get(word);
			} 
		}*/
	    BufferedWriter bw = null;
	    System.out.println("开始写入JSon");
	    
		File sourceFile = new File("E:"+"/"+"wordCloud.json");//写入JSON文件
        if(sourceFile.exists()){
          sourceFile.delete();
        }
        FileWriter fr = new FileWriter(sourceFile);
        bw = new BufferedWriter(fr);
        String str="";
        str="{\"children\":[";
        int length=map.size();
        for (String word:map.keySet()) {
        	if (length>1) {
        		str+="{ \"name\": \""+word+"\", 	\"weight\": "+map.get(word)+" },";
        		length=length-1;
			} else {
				str+="{ \"name\": \""+word+"\", 	\"weight\": "+map.get(word)+" }";
			}
			
		}
        str+="]}";
        fr.write(str);
        bw.close();
        fr.close();
        
        sourceFile = new File("E:"+"/"+"wordCloud2.json");//写入JSON文件
        if(sourceFile.exists()){
          sourceFile.delete();
        }
        fr = new FileWriter(sourceFile);
        bw = new BufferedWriter(fr);
        String str2="";
        str2="{\"children\":[";
        int length2=map2.size();
        for (String word:map2.keySet()) {
        	if (length2>1) {
        		str2+="{ \"name\": \""+word+"\", 	\"weight\": "+map.get(word)+" },";
        		length2=length2-1;
			} else {
				str2+="{ \"name\": \""+word+"\", 	\"weight\": "+map.get(word)+" }";
			}
			
		}
        str2+="]}";
        fr.write(str2);
        bw.close();
        fr.close();
        System.out.println("JSon写入完毕");
	}
	
	
	
	
	//map排序算法
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Float> sortMap(Map<String, Float> oldMap) {   //对MAP中的键值对按VAlue值进行排序 
        ArrayList<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Float> arg0,  
                    Entry<java.lang.String, Float> arg1) {  
                return arg0.getValue().compareTo(arg1.getValue());  
            }  
        });  
        Map newMap = new LinkedHashMap();  
        for (int i = list.size()-1; i >=0 ; i--) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }
	
}
