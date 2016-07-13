package demo.service.mongodb;
 
import com.mongodb.DBObject;
import demo.service.mongodb.pojo.Point;

import java.util.List;
 
/**
 * Mondodb 地址运算 接口类
 *
 * @author babylon
 * @version 1.1
 * @date 2016年7月12日-下午1:42:06
 */
public interface MongoGeoDao extends MongoDao{
	
	 /**
     * 聚合查询，查询一个点附近的点，并返回每一个点到该中心点的距离，在坐标表分片的情况下$nearSphere不支持，
     * 可以使用该方法进行查询
     * @param collection    集合名称
     * @param query         查询条件
     * @param point         中心点坐标
     * @param limit         返回记录数量限制
     * @param maxDistance   最大距离
     * @return              非NULL的list
     */
    public List<DBObject> geoNear(String collection, DBObject query, Point point, int limit, long maxDistance) ;
	
    /**
     * 查询在圆形区域内的坐标点，需要指定中心点坐标和半径，半径单位是米
     *
     * @param collection    集合名称
     * @param locationField 坐标字段
     * @param center        中心点坐标[经度，纬度]
     * @param radius        半径 单位:米
     * @param fields        查询字段
     * @param query         查询条件
     * @param limit         返回记录限制数量
     * @return              非NULL的list
     */
    public List<DBObject> withinCircle(String collection, String locationField, Point center, long radius,
                                       DBObject fields, DBObject query, int limit);
 
    /**
     * 指定一个点，返回该点附近的坐标点且是由近到远,$nearSphere 需要建立索引2dsphere 或者2d,并且支持GeoJSON和一般坐标对
     * 注意: $nearSphere在分片的集群中无效，使用geoNear
     *
     * @param collection    集合名称
     * @param locationField 坐标字段
     * @param center        中心点坐标[经度，纬度]
     * @param minDistance   最近距离
     * @param maxDistance   最远距离
     * @param query         查询条件
     * @param fields        查询字段
     * @param limit         返回记录限制数量
     * @return              非NULL的list
     */
    public List<DBObject> nearSphere(String collection, String locationField, Point center, long minDistance, long maxDistance, DBObject query, DBObject fields, int limit);
 
 
    /**
     * 查询位于指定一个封闭多边形内的所有坐标点，给定的多边形坐标点必须首位相接形成封闭的多边形
     * 如三角形
     *       final LinkedList<double[]> polygon = new LinkedList<>();
     *       polygon.addLast(new double[] {  121.36, 31.18 });
     *       polygon.addLast(new double[] {  121.35, 31.36 });
     *       polygon.addLast(new double[] {  121.39, 31.17 });
     *       polygon.addLast(new double[] {  121.36, 31.18 });
     *
     * MongoDB将多边形的边界也作为查询形状的一部分
     * @param collection    集合名称
     * @param locationField 坐标字段
     * @param polygon       多边形坐标
     * @param fields        查询字段
     * @param query         查询条件
     * @param limit         返回记录限制数量
     * @return              非NULL的list
     */
    public List<DBObject> withinPolygon(String collection, String locationField,
                                        List<double[]> polygon, DBObject fields, DBObject query, int limit);
 
 
    /**
     * 查询位于指定多个封闭多边形内的所有坐标点，给定的多边形坐标点必须首位相接形成封闭的多边形
     * @param collection    集合名称
     * @param locationField 坐标字段
     * @param polygons      多边形坐标 数组
     * @param fields        查询字段
     * @param query         查询条件
     * @param limit         返回记录限制数量
     * @return              非NULL的list
     */
    public List<DBObject> withinMultiPolygon(String collection, String locationField,
                                             List<List<double[]>> polygons, DBObject fields, DBObject query, int limit);
 
 
    /**
     * 在矩形区域内查找坐标点，该方法仅仅在2d索引是支持，在2dsphere中不支持
     * @param collection    集合名称
     * @param locationField 坐标字段
     * @param bottomLeft    左下角
     * @param upperRight    右上角
     * @param fields        查询字段
     * @param query         查询条件
     * @param limit         返回记录限制数量
     * @return              非NULL的list
     */
    @Deprecated
    public List<DBObject> withinBox(String collection, String locationField,
                                    Point bottomLeft, Point upperRight, DBObject fields, DBObject query, int limit);
 
}