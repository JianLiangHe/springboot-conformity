package edu.springboot.controller;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Test implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重复次数
	private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		// 部分二：定义如何抽取页面信息，在这里编写逻辑
		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		
		if (page.getResultItems().get("name") == null) {
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
		
		// 部分三：从页面发现后续的url地址来抓取
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
	}

	public static void main(String[] args) {
		Spider.create(new Test())
				.addUrl("https://github.com/code4craft")
				.thread(5)
				.run();
	}
	
}
