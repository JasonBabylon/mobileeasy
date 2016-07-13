package demo.service.mongodb;

import java.util.List;

import com.mongodb.DBObject;

/**
 * mongodb 父接口类
 * @author babylon
 * @version 1.1
 * @date 2016年7月12日-下午1:22:23
 */
public interface  MongoDao {
	
	public DBObject findOne(String collection, DBObject query, DBObject fields);
	 
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int pageNum, int pageSize);
 
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int limit);
 
    public void delete(String collection, DBObject dbObject);
 
    public void insert(String collection, DBObject dbObject);
 
    public void update(String collection, DBObject query, DBObject update, boolean upsert, boolean multi);
 
    public Long count(String collection, DBObject query);
 
    public List<?> distinct(String collection, String key, DBObject query);

	/**
	 * 批量插入
	 * @param collection  集合名称
	 * @param list	 数据集合
	 */
	void insertBatch(String collection, List<DBObject> list);

	/**
	 * 如果dbObject 有ID，则对应刷新数据库中对应的值，没有的话则新增记录
	 * @param collection 集合名称
	 * @param dbObject 数据对象
	 */
	void save(String collection, DBObject dbObject);
 
}