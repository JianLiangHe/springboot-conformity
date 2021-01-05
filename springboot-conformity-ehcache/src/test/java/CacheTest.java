import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheTest {

	public static void main(String[] args) {
		// 创建缓存管理器
		CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
		
		// 获取缓存对象
		Cache cache = cacheManager.getCache("bookList");
		
		// 创建元素
		Element element = new Element("key1", "hello world");
		
		// 将元素添加到缓存
		cache.put(element);
		
		// 获取缓存
		Element value = cache.get("key1");
		System.out.println("value: " + value);
		System.out.println(value.getObjectValue());
		
		// 删除缓存
		cache.remove("key1");
		
		// 刷新缓存
		cache.flush();
		
		// 关闭缓存管理器
		cacheManager.shutdown();
	}
	
}
