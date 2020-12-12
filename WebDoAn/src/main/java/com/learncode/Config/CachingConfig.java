package com.learncode.Config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;
@Component
public class CachingConfig implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

	@Override
	public void customize(ConcurrentMapCacheManager cacheManager) {
		// TODO Auto-generated method stub
		cacheManager.setCacheNames(Arrays.asList("chucnang","sanpham","vaitro","nhomnguoidung","nguoidung"));
	}

	

}
