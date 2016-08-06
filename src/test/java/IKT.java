
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.fp.domain.Reply;
import com.fp.domain.Topic;

public class IKT {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 读取积极词典
		HashSet<String> positiveDic = new HashSet<String>();
		File pdic = new File("src/main/resources/positive.dic");
		Scanner psc = new Scanner(pdic);
		while (psc.hasNextLine()) {
			String word = psc.nextLine();
			if (word.length() > 1) {
				positiveDic.add(word);
			}
		}
		psc.close();
		// 读取消极词典
		HashSet<String> negativeDic = new HashSet<String>();
		File ndic = new File("src/main/resources/negative.dic");
		Scanner nsc = new Scanner(ndic);
		while (nsc.hasNextLine()) {
			String word = nsc.nextLine();
			if (word.length() > 1) {
				negativeDic.add(word);
			}
		}
		nsc.close();
		// 取数据
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		List<Topic> topicList = session.createCriteria(Topic.class).list();
		Iterator<Topic> ti = topicList.iterator();
		while (ti.hasNext()) {
			Topic topic = (Topic) ti.next();
			List<Reply> replyList = session.createCriteria(Reply.class).createAlias("topic", "t").add(Restrictions.eq("t.id", topic.getId())).list();
			Iterator<Reply> ri = replyList.iterator();
			String text = new String();
			while (ri.hasNext()) {
				Reply r = (Reply) ri.next();
				text = text.concat(r.getContent());
			}
			// 开始分词
			StringReader sr = new StringReader(text);
			IKSegmenter ik = new IKSegmenter(sr, true);
			Lexeme lex = null;
			int p = 0;
			int n = 0;
			while ((lex = ik.next()) != null) {
				String lexemeText = lex.getLexemeText();
				if (lexemeText.length() > 1) {
					if (positiveDic.contains(lexemeText)) {
						p++;
					}
					if (negativeDic.contains(lexemeText)) {
						n++;
					}
				}
			}
			System.out.println("积极词：" + p);
			System.out.println("消极词：" + n);
			if (p != 0 || n != 0) {
				topic.setPositive((p * 1.0) / ((p + n) * 1.0) * 100.0);
				topic.setNegative((n * 1.0) / ((p + n) * 1.0) * 100.0);
			}
			session.save(topic);
		}
		tx.commit();
		session.close();
		ctx.close();
	}

}
