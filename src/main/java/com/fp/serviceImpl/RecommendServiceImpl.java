package com.fp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.Feel;
import com.fp.domain.Info;
import com.fp.domain.User;
import com.fp.service.RecommendService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class RecommendServiceImpl extends DaoSupportImpl<User> implements RecommendService {
	
	@Override
	public List<Feel> findRecommend(User user) {
		return getSession().createQuery(
				"FROM Feel WHERE user.id = ?")
				.setParameter(0, user.getId())
				.list();
	}

	@Override
	public List<Info> recommend(User user) {
		Info info = (Info) getSession().createQuery(
				"FROM Info WHERE id = ?")
				.setParameter(0, user.getId())
				.uniqueResult();
		
		/* 择偶条件
		 * mateName 年龄
		 * mateHeight
		 * mateEduction
		 * mateSalary
		 * mateLive
		 * mateMarried
		 * mateHourse
		 * mateIsChildren
		 * */
		String hql = "FROM Info";
		StringBuilder stbu = new StringBuilder(hql);
		int index,low,high;
		
		/*1.筛选年龄*/
		String condition = info.getMateName();
		if(null != condition && !"不限".equals(condition)
				&& condition.matches("[0-9]{1,3}~[0-9]{1,3}&nbsp;岁")){
			
			index = condition.indexOf('~');
			low = Integer.parseInt(condition.substring(0, index));
			high = Integer.parseInt(condition.substring(index + 1, index + 3));
			
			if(low <= high)
				/*sql里的substring小从1开始*/
				stbu.append(" WHERE CAST(SUBSTRING(age,1,2) AS int) BETWEEN ")
					.append(low).append(" and ").append(high);
		}
		
		/*2.筛选身高*/
		/*condition = info.getMateHeight();
		if(null != condition && !"不限".equals(condition)
				&& condition.matches("[0-9]{2,3}~[0-9]{2,3}&nbsp;厘米")){
			
			index = condition.indexOf('~');
			low = Integer.parseInt(condition.substring(0,index));
			high = Integer.parseInt(condition.substring(index + 1, index + 4));
			
			if(low <= high){
				if(hql.length() == stbu.length())
					stbu.append(" WHERE");
				else
					stbu.append(" AND");
				
				stbu.append(" CAST(SUBSTRING(height,1,3) AS int) BETWEEN ")
					.append(low).append(" and ").append(high);
			}
		}*/
		
		/*3.筛选学历*/
		condition = info.getMateEduction();
		if(null != condition && !"不限".equals(condition)){
			String[] education = condition.split(",");
			
			if(0 < education.length){
				if(hql.length() == stbu.length())
					stbu.append(" WHERE");
				else
					stbu.append(" AND");
				
				stbu.append(" education in(");
				for (String str : education) {
					stbu.append("'").append(str).append("'");
					
					if(!str.equals(education[education.length - 1]))
						stbu.append(",");
				}
				stbu.append(")");
			}
		}
		
		/*4.筛选工资*/
		condition = info.getMateSalary();
		
		/*5.筛选地域*/
		condition = info.getMateLive();
		if(null != condition && !"不限".equals(condition) && !"中国".equals(condition)){
			if(hql.length() == stbu.length())
				stbu.append(" WHERE");
			else
				stbu.append(" AND");
			
			stbu.append(" SUBSTRING(liveCity,1,2)='")
				.append(condition.substring(0,2)).append("'");
		}
		
		/*6.筛选婚姻情况*/
		condition = info.getMateMarried();
		if(null != condition && !"不限".equals(condition)){
			String[] str = condition.split(",");
			
			if(hql.length() == stbu.length())
				stbu.append(" WHERE");
			else
				stbu.append(" AND");
			
			stbu.append(" Ismarraied IN(");
			for (String string : str)
				stbu.append("'").append(string).append("',");
			
			stbu.replace(stbu.length() - 1, stbu.length(), "");
			stbu.append(")");
		}

		/*7.筛选房屋*/
		
		/*8.筛选孩子情况*/
		
		/*9.性别*/
		int gender = info.getSex();
		
		if(0 == gender)
			gender++;
		else
			gender--;
		
		if(hql.length() == stbu.length())
			stbu.append(" WHERE");
		else
			stbu.append(" AND");
		
		stbu.append(" sex = ").append(gender);
		
		return getSession().createQuery(stbu.toString())
				.list();
	}
	
	public static void main(String[] args) {
		StringBuilder stbu = new StringBuilder("FROM Info ");
		System.out.println(stbu.length());
		String[] str = "大专".split(",");
		System.out.println(str.length);
		System.out.println("29~31&nbsp;岁".matches("[0-9]{1,3}~[0-9]{1,3}&nbsp;岁"));
		
		
	}

	
	@Override
	public void setFeel(long userId, long recId, String feel) {
		getSession().createSQLQuery(
			"UPDATE feel SET feel = ? WHERE userId = ? AND recommenderId = ?")
			.setParameter(0, feel)
			.setParameter(1, userId)
			.setParameter(2, recId)
			.executeUpdate();
	}

}
