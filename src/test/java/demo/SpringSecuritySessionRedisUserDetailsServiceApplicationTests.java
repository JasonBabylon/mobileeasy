package demo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import demo.service.mongodb.MongoGeoDao;
import demo.service.mongodb.pojo.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringSecuritySessionRedisUserDetailsServiceApplication.class)
public class SpringSecuritySessionRedisUserDetailsServiceApplicationTests {

	@Autowired
	private MongoGeoDao mongoGeoDao;

	@Test
	public void contextLoads() {
	}


	/**
	 * MongoDB 使用方法
	 *
	 * 该Test在 Idea环境下会报缺失类的异常，直接部署，通过Contoller进行测试通过。
	 *
	 * 具体数据初始化参考博客：http://blog.csdn.net/jack85986370/article/details/51889459
	 *
	 */
	@Test
	public void mongoTest() {
		DBObject query = new BasicDBObject();
		Point point = new Point();
		point.setLng(120.213888);
		point.setLat(30.197689);
		int limit = 10;
		Long maxDistance = 5000L; // 米
		List<DBObject> list = mongoGeoDao.geoNear("point.test", query, point, limit, maxDistance);
		for(DBObject obj : list){
			System.out.println(obj);
		}
	}

}
