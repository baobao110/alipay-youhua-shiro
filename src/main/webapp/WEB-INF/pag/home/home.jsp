<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>首页</title>

		<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />

		<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />

		<link href="../css/hmstyle.css" rel="stylesheet" type="text/css" />
		<script src="../AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="../AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>
	</head>

	<body>
		<div class="hmtop">
			<!--顶部导航条 -->
			<div class="am-container header">
				<ul class="message-l">
					<div class="topMessage">
						<div class="menu-hd">
							<a href="#" target="_top" class="h">亲，请登录</a>
							<a href="#" target="_top">免费注册</a>
						</div>
					</div>
				</ul>
				<ul class="message-r">
					<div class="topMessage home">
						<div class="menu-hd"><a href="#" target="_top" class="h">商城首页</a></div>
					</div>
					<div class="topMessage my-shangcheng">
						<div class="menu-hd MyShangcheng"><a href="#" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
					</div>
					<div class="topMessage mini-cart">
						<div class="menu-hd"><a id="mc-menu-hd" href="#" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
					</div>
					<div class="topMessage favorite">
						<div class="menu-hd"><a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a></div>
				</ul>
				</div>

				<!--悬浮搜索框-->

				<div class="nav white">
					<div class="logo"><img src="/images/logo.png" /></div>
					<div class="logoBig">
						<li><img src="../images/logobig.png" /></li>
					</div>

					<div class="search-bar pr">
						<a name="index_none_header_sysc" href="#"></a>
						<form>
							<input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
							<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
						</form>
					</div>
				</div>

				<div class="clear"></div>
			</div>
			
			
			<div class="banner">
                      <!--轮播 -->
						<div class="am-slider am-slider-default scoll" data-am-flexslider id="demo-slider-0">
							<ul class="am-slides">
								<li class="banner1"><a href="introduction.html"><img src="../images/ad1.jpg" /></a></li>
								<li class="banner2"><a><img src="../images/ad2.jpg" /></a></li>
								<li class="banner3"><a><img src="../images/ad3.jpg" /></a></li>
								<li class="banner4"><a><img src="../images/ad4.jpg" /></a></li>

							</ul>
						</div>
						<div class="clear"></div>	
			</div>						
			
			<div class="shopNav">
				<div class="slideall">
			        
					   <div class="long-title"><span class="all-goods">全部分类</span></div>
					   <div class="nav-cont">
							<ul>
								<li class="index"><a href="#">首页</a></li>
                                <li class="qc"><a href="#">闪购</a></li>
                                <li class="qc"><a href="#">限时抢</a></li>
                                <li class="qc"><a href="#">团购</a></li>
                                <li class="qc last"><a href="#">大包装</a></li>
							</ul>
						    <div class="nav-extra">
						    	<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>我的福利
						    	<i class="am-icon-angle-right" style="padding-left: 10px;"></i>
						    </div>
						</div>
		        				
						<!--侧边导航 -->
						<div id="nav" class="navfull"  >
							<div class="area clearfix">
								<div class="category-content" id="guide_2">
									
									<div class="category">
									<ul class="category-list" id="js_climit_li"  v-for="list in List">
										<li  class="`appliance js_toggle relative first" >
												<div class="category-info">
													<h3 class="category-name b-category-name"><i><img src="/images/cake.png"></i><a class="ml-22">{{list.catalogName}}</a></h3>
													<em>&gt;</em></div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<dl class="dl-sort" v-for="dto in list.dto"><!--注意这里的for循环嵌套   -->
																		<dt><span v-bind:title="dto.catalogName">{{dto.catalogName}}</span></dt><!--注意这里的vue的v-bind的赋值 https://cn.vuejs.org/v2/guide/syntax.html#使用-JavaScript-表达式-->
																	</dl>
																</div>
															</div>
														</div>
													</div>
												</div>
											<b class="arrow"></b>	
											</li>
										</ul>
									</div>
								</div>

							</div>
						</div>


					<!--小导航 -->
					<div class="am-g am-g-fixed smallnav">
						<div class="am-u-sm-3">
							<a href="sort.html"><img src="../images/navsmall.jpg" />
								<div class="title">商品分类</div>
							</a>
						</div>
						<div class="am-u-sm-3">
							<a href="#"><img src="../images/huismall.jpg" />
								<div class="title">大聚惠</div>
							</a>
						</div>
						<div class="am-u-sm-3">
							<a href="#"><img src="../images/mansmall.jpg" />
								<div class="title">个人中心</div>
							</a>
						</div>
						<div class="am-u-sm-3">
							<a href="#"><img src="../images/moneysmall.jpg" />
								<div class="title">投资理财</div>
							</a>
						</div>
					</div>

					<!--走马灯 -->

					<div class="marqueen">
						<span class="marqueen-title">商城头条</span>
						<div class="demo">

							<ul>
								<li class="title-first"><a target="_blank" href="#">
									<img src="../images/TJ2.jpg"></img>
									<span>[特惠]</span>商城爆品1分秒								
								</a></li>
								<li class="title-first"><a target="_blank" href="#">
									<span>[公告]</span>商城与广州市签署战略合作协议
								     <img src="../images/TJ.jpg"></img>
								     <p>XXXXXXXXXXXXXXXXXX</p>
							    </a></li>
							    
						<div class="mod-vip">
							<div class="m-baseinfo">
								<a href="../person/index.html">
									<img src="../images/getAvatar.do.jpg">
								</a>
								<em>
									Hi,<span class="s-name">小叮当</span>
									<a href="#"><p>点击更多优惠活动</p></a>									
								</em>
							</div>
							<div class="member-logout">
								<a class="am-btn-warning btn" href="login.html">登录</a>
								<a class="am-btn-warning btn" href="register.html">注册</a>
							</div>
							<div class="member-login">
								<a href="#"><strong>0</strong>待收货</a>
								<a href="#"><strong>0</strong>待发货</a>
								<a href="#"><strong>0</strong>待付款</a>
								<a href="#"><strong>0</strong>待评价</a>
							</div>
							<div class="clear"></div>	
						</div>																	    
							    
								<li><a target="_blank" href="#"><span>[特惠]</span>洋河年末大促，低至两件五折</a></li>
								<li><a target="_blank" href="#"><span>[公告]</span>华北、华中部分地区配送延迟</a></li>
								<li><a target="_blank" href="#"><span>[特惠]</span>家电狂欢千亿礼券 买1送1！</a></li>
								
							</ul>
                        <div class="advTip"><img src="../images/advTip.jpg"/></div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="shopMainbg">
				<div class="shopMain" id="shopmain">

					<!--今日推荐 -->

					<div class="am-g am-g-fixed recommendation">
						<div class="clock am-u-sm-3" ">
							<img src="../images/2016.png "></img>
							<p>今日<br>推荐</p>
						</div>
						<div class="am-u-sm-4 am-u-lg-3 ">
							<div class="info ">
								<h3>真的有鱼</h3>
								<h4>开年福利篇</h4>
							</div>
							<div class="recommendationMain ">
								<img src="/images/tj.png "></img>
							</div>
						</div>						
						<div class="am-u-sm-4 am-u-lg-3 ">
							<div class="info ">
								<h3>囤货过冬</h3>
								<h4>让爱早回家</h4>
							</div>
							<div class="recommendationMain ">
								<img src="../images/tj1.png "></img>
							</div>
						</div>
						<div class="am-u-sm-4 am-u-lg-3 ">
							<div class="info ">
								<h3>浪漫情人节</h3>
								<h4>甜甜蜜蜜</h4>
							</div>
							<div class="recommendationMain ">
								<img src="../images/tj2.png "></img>
							</div>
						</div>

					</div>
					<div class="clear "></div>
					<!--热门活动 -->

					<div class="am-container activity ">
						<div class="shopTitle ">
							<h4>活动</h4>
							<h3>每期活动 优惠享不停 </h3>
							<span class="more ">
                              <a class="more-link " href="# ">全部活动</a>
                            </span>
						</div>
					
					  <div class="am-g am-g-fixed ">
						<div class="am-u-sm-3 ">
							<div class="icon-sale one "></div>	
								<h4>秒杀</h4>							
							<div class="activityMain ">
								<img src="../images/activity1.jpg "></img>
							</div>
							<div class="info ">
								<h3>春节送礼优选</h3>
							</div>														
						</div>
						
						<div class="am-u-sm-3 ">
						  <div class="icon-sale two "></div>	
							<h4>特惠</h4>
							<div class="activityMain ">
								<img src="../images/activity2.jpg "></img>
							</div>
							<div class="info ">
								<h3>春节送礼优选</h3>								
							</div>							
						</div>						
						
						<div class="am-u-sm-3 ">
							<div class="icon-sale three "></div>
							<h4>团购</h4>
							<div class="activityMain ">
								<img src="../images/activity3.jpg "></img>
							</div>
							<div class="info ">
								<h3>春节送礼优选</h3>
							</div>							
						</div>						

						<div class="am-u-sm-3 last ">
							<div class="icon-sale "></div>
							<h4>超值</h4>
							<div class="activityMain ">
								<img src="/images/activity.jpg "></img>
							</div>
							<div class="info ">
								<h3>春节送礼优选</h3>
							</div>													
						</div>

					  </div>
                   </div>
					<div class="clear "></div>

					<!--甜点-->	
	<div id ='books'>	
		<div  v-for="(flowx,index) in Flow"><!-- 注意这里的id和v-for的位置关系以及index的作用是数组中的i -->
			<div v-if="index % 3 == 0">	
				<div class="am-container " >
					<div class="shopTitle ">
					<h4>{{flowx.catalog}}</h4>
					 <h3>每一本书都有一个故事</h3>
					<div class="today-brands ">
						<a href="# ">桂花糕</a>
						<a href="# ">奶皮酥</a>
						<a href="# ">栗子糕 </a>
						<a href="# ">马卡龙</a>
						<a href="# ">铜锣烧</a>
						<a href="# ">豌豆黄</a>
					</div>
					<span class="more ">
		                    <a class="more-link " href="# ">更多美味</a>
		                        </span>
				</div>
			</div>
		
			<div class="am-g am-g-fixed floodOne ">
				<div class="am-u-sm-5 am-u-md-3 am-u-lg-4 text-one " >
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[0].id"><!-- 打开新页面 -->
						<div class="outer-con ">
							<div class="title ">
								{{flowx.dto[0].bookName}}
							</div>
							<div class="sub-title ">
								仅售：{{flowx.dto[0].bookPrice}}
							</div>
						</div>
					<img height="280px" width="280px"  v-lazy="'http://192.168.111.128/'+flowx.dto[0].pictureName" /></a><!--注意这里的vlazy是用于懒加载   -->
				</div>
			
				
			<div class="am-u-sm-7 am-u-md-5 am-u-lg-4">
					<div class="text-two">
						<div class="outer-con ">
							<div class="title ">
								{{flowx.dto[1].bookName}}
							</div>
							<div class="sub-title ">
								仅售：{{flowx.dto[1].bookPrice}}
							</div>
		
						</div>
						<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[1].id"><img height="280px" width="280px" v-lazy="'http://192.168.111.128/'+flowx.dto[1].pictureName"/></a>
					</div>
					<div class="text-two last">
						<div class="outer-con ">
							<div class="title ">
							{{flowx.dto[2].bookName}}
							</div>
							<div class="sub-title ">
								仅售：{{flowx.dto[2].bookPrice}}
							</div>
		
						</div>
						<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[2].id"><img height="280px" width="280px" v-lazy="'http://192.168.111.128/'+flowx.dto[2].pictureName" /></a>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-4 ">
					<div class="am-u-sm-3 am-u-md-6 text-three">
						<div class="outer-con ">
							<div class="title ">
								{{flowx.dto[3].bookName}}
							</div>
		
							<div class="sub-title ">
								仅售：{{flowx.dto[3].bookPrice}}
							</div>
						</div>
						<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[3].id"><img height="180px" width="180px" v-lazy="'http://192.168.111.128/'+flowx.dto[3].pictureName" /></a>
					</div>
		
					<div class="am-u-sm-3 am-u-md-6 text-three">
						<div class="outer-con ">
							<div class="title ">
								{{flowx.dto[4].bookName}}
							</div>
		
							<div class="sub-title ">
								仅售：{{flowx.dto[4].bookPrice}}
							</div>
						</div>
						<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[4].id"><img height="180px" width="180px" v-lazy="'http://192.168.111.128/'+flowx.dto[4].pictureName" /></a>
					</div>
		
					<div class="am-u-sm-3 am-u-md-6 text-three">
						<div class="outer-con ">
							<div class="title ">
								{{flowx.dto[5].bookName}}
							</div>
		
							<div class="sub-title ">
								仅售：{{flowx.dto[5].bookPrice}}
							</div>
						</div>
						<<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[5].id"><img height="180px" width="180px" v-lazy="'http://192.168.111.128/'+flowx.dto[5].pictureName" /></a>
					</div>
					
					<div class="am-u-sm-3 am-u-md-6 text-three">
						<div class="outer-con ">
							<div class="title ">
								{{flowx.dto[6].bookName}}
							</div>
		
							<div class="sub-title ">
								仅售：{{flowx.dto[6].bookPrice}}
							</div>
						</div>
						<<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[6].id"><img height="180px" width="180px" v-lazy="'http://192.168.111.128/'+flowx.dto[6].pictureName" /></a><!-- 注意这里的图片地址格式 -->
					</div>
				</div>
			</div>
			<div class="clear "></div>
		</div>
			<!--坚果-->
		<div v-if="index % 3 == 1">	
			<div class="am-container ">
				<div class="shopTitle ">
					<h4>{{flowx.catalog}}</h4>
					 <h3>每一本书都有一个故事</h3>
					<div class="today-brands ">
						<a href="# ">腰果</a>
						<a href="# ">松子</a>
						<a href="# ">夏威夷果 </a>
						<a href="# ">碧根果</a>
						<a href="# ">开心果</a>
						<a href="# ">核桃仁</a>
					</div>
					<span class="more ">
		                    <a class="more-link " href="# ">更多美味</a>
		                        </span>
				</div>
			</div>
			<div class="am-g am-g-fixed floodTwo ">
		
		
				<div class="am-u-sm-5 am-u-md-4 text-one ">
						<div class="outer-con ">
							<div class="title ">	<!-- 注意这里的数组 -->
								{{flowx.dto[0].bookName}}
							</div>
							<div class="sub-title ">
								仅售：{{flowx.dto[0].bookPrice}}
							</div>
		
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[0].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[0].pictureName"/></div>
					</a>
				</div>
				<div class="am-u-sm-7 am-u-md-4 am-u-lg-2 text-two">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[1].bookName}}
						</div>
		
						<div class="sub-title ">
							仅售：{{flowx.dto[1].bookPrice}}
						</div>
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[1].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[1].pictureName"/></a>
				</div>
		
				<div class="am-u-md-4 am-u-lg-2 text-three">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[2].bookName}}
						</div>
		
						<div class="sub-title ">
							仅售：{{flowx.dto[2].bookPrice}}
						</div>
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[2].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[2].pictureName"/></a>
				</div>
				<div class="am-u-md-4 am-u-lg-2 text-three">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[3].bookName}}
						</div>
		
						<div class="sub-title ">
							仅售：{{flowx.dto[3].bookPrice}}
						</div>
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[3].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[3].pictureName"/></a>
				</div>
				<div class="am-u-sm-6 am-u-md-4 am-u-lg-2 text-two ">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[4].bookName}}
						</div>
		
						<div class="sub-title ">
							仅售：{{flowx.dto[4].bookPrice}}
						</div>
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[4].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[4].pictureName"/></a>
				</div>
				<div class="am-u-sm-6 am-u-md-3 am-u-lg-2 text-four ">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[5].bookName}}
						</div>
		
						<div class="sub-title ">
							仅售：{{flowx.dto[5].bookPrice}}
						</div>
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[5].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[5].pictureName"/></a>
				</div>
				<div class="am-u-sm-4 am-u-md-3 am-u-lg-4 text-five">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[6].bookName}}
						</div>
						<div class="sub-title ">
							仅售：{{flowx.dto[6].bookPrice}}
						</div>
		
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[6].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[6].pictureName"/></a>
				</div>
				<div class="am-u-sm-4 am-u-md-3 am-u-lg-2 text-six">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[7].bookName}}
						</div>
		
						<div class="sub-title ">
							仅售：{{flowx.dto[7].bookPrice}}
						</div>
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[7].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[7].pictureName"/></a>
				</div>
				<div class="am-u-sm-4 am-u-md-3 am-u-lg-4 text-five">
					<div class="outer-con ">
						<div class="title ">
							{{flowx.dto[8].bookName}}
						</div>
						<div class="sub-title ">
							仅售：{{flowx.dto[8].bookPrice}}
						</div>
		
					</div>
					<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+flowx.dto[8].id"><img v-lazy="'http://192.168.111.128/'+flowx.dto[8].pictureName"/></a><!-- 注意这里图片是放在linux上的用ngiux地址为服务器的地址 -->
				</div>
			</div>
		
			<div class="clear "></div>
		</div>                                                                                                                                                                                                                  
		
		<div v-if="index % 3 == 2">	
			<div class="am-container ">
				<div class="shopTitle ">
					<h4>{{flowx.catalog}}</h4>
					 <h3>每一本书都有一个故事</h3>
					<div class="today-brands ">
						<a href="# ">小鱼干</a>
						<a href="# ">海苔</a>
						<a href="# ">鱿鱼丝</a>
						<a href="# ">海带丝</a>
					</div>
					<span class="more ">
		                    <a class="more-link " href="# ">更多美味</a>
		                        </span>
				</div>
			</div>
			<div class="am-g am-g-fixed flood method3 ">
					<ul class="am-thumbnails ">
						<li v-for="dto2 in flowx.dto">
							<div class="list ">
								<a target="_blank" v-bind:href="'/Page/toInformation.do?commodityId='+dto2.id">
									<img v-lazy="'http://192.168.111.128/'+dto2.pictureName"/>
									<div class="pro-title ">{{dto2.bookName}}</div>
									<span class="e-price ">￥{{dto2.bookPrice}}</span>
								</a>
							</div>
							</li>
						</ul>
				</div>
			</div>
		</div>
	</div>


	<div class="footer ">
		<div class="footer-hd ">
			<p>
				<a href="# ">恒望科技</a>
				<b>|</b>
				<a href="# ">商城首页</a>
				<b>|</b>
				<a href="# ">支付宝</a>
				<b>|</b>
				<a href="# ">物流</a>
			</p>
		</div>
		<div class="footer-bd ">
			<p>
				<a href="# ">关于恒望</a>
				<a href="# ">合作伙伴</a>
				<a href="# ">联系我们</a>
				<a href="# ">网站地图</a>
				<em>© 2015-2025 Hengwang.com 版权所有</em>
			</p>
		</div>
	</div>
</div>
</div>
</div>
</div>
<!--引导 -->

<div class="navCir">
	<li class="active"><a href="home3.html"><i class="am-icon-home "></i>首页</a></li>
	<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
	<li><a href="shopcart.html"><i class="am-icon-shopping-basket"></i>购物车</a></li>
	<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>
</div>
<!--菜单 -->
<div class=tip>
	<div id="sidebar">
		<div id="wrap">
			<div id="prof" class="item ">
				<a href="# ">
					<span class="setting "></span>
				</a>
				<div class="ibar_login_box status_login ">
					<div class="avatar_box ">
						<p class="avatar_imgbox "><img src="/images/no-img_mid_.jpg " /></p>
						<ul class="user_info ">
							<li>用户名：sl1903</li>
							<li>级&nbsp;别：普通会员</li>
						</ul>
					</div>
					<div class="login_btnbox ">
						<a href="# " class="login_order ">我的订单</a>
						<a href="# " class="login_favorite ">我的收藏</a>
					</div>
					<i class="icon_arrow_white "></i>
				</div>

			</div>
			<div id="shopCart " class="item ">
				<a href="">
					<span class="message "></span>
				</a>
				<p>
					购物车
				</p>
				<p class="cart_num ">0</p>
			</div>
			<div id="asset " class="item ">
				<a href="# ">
					<span class="view "></span>
				</a>
				<div class="mp_tooltip ">
					我的资产
					<i class="icon_arrow_right_black "></i>
				</div>
			</div>

			<div id="foot " class="item ">
				<a href="# ">
					<span class="zuji "></span>
				</a>
				<div class="mp_tooltip ">
					我的足迹
					<i class="icon_arrow_right_black "></i>
				</div>
			</div>

			<div id="brand " class="item ">
				<a href="#">
					<span class="wdsc "><img src="/images/wdsc.png " /></span>
				</a>
				<div class="mp_tooltip ">
					我的收藏
					<i class="icon_arrow_right_black "></i>
				</div>
			</div>

			<div id="broadcast " class="item ">
				<a href="# ">
					<span class="chongzhi "><img src="/images/chongzhi.png " /></span>
				</a>
				<div class="mp_tooltip ">
					我要充值
					<i class="icon_arrow_right_black "></i>
				</div>
			</div>

			<div class="quick_toggle ">
				<li class="qtitem ">
					<a href="# "><span class="kfzx "></span></a>
					<div class="mp_tooltip ">客服中心<i class="icon_arrow_right_black "></i></div>
				</li>
				<!--二维码 -->
				<li class="qtitem ">
					<a href="#none "><span class="mpbtn_qrcode "></span></a>
					<div class="mp_qrcode " style="display:none; "><img src="/images/weixin_code_145.png " /><i class="icon_arrow_white "></i></div>
				</li>
				<li class="qtitem ">
					<a href="#top " class="return_top "><span class="top "></span></a>
				</li>
			</div>

			<!--回到顶部 -->
			<div id="quick_links_pop " class="quick_links_pop hide "></div>

		</div>

	</div>
	<div id="prof-content " class="nav-content ">
		<div class="nav-con-close ">
			<i class="am-icon-angle-right am-icon-fw "></i>
		</div>
		<div>
			我
		</div>
	</div>
	<div id="shopCart-content " class="nav-content ">
		<div class="nav-con-close ">
			<i class="am-icon-angle-right am-icon-fw "></i>
		</div>
		<div>
			购物车
		</div>
	</div>
	<div id="asset-content " class="nav-content ">
		<div class="nav-con-close ">
			<i class="am-icon-angle-right am-icon-fw "></i>
		</div>
		<div>
			资产
		</div>

		<div class="ia-head-list ">
			<a href="# " target="_blank " class="pl ">
				<div class="num ">0</div>
				<div class="text ">优惠券</div>
			</a>
			<a href="# " target="_blank " class="pl ">
				<div class="num ">0</div>
				<div class="text ">红包</div>
			</a>
			<a href="# " target="_blank " class="pl money ">
				<div class="num ">￥0</div>
				<div class="text ">余额</div>
			</a>
		</div>

	</div>
	<div id="foot-content " class="nav-content ">
		<div class="nav-con-close ">
			<i class="am-icon-angle-right am-icon-fw "></i>
		</div>
		<div>
			足迹
		</div>
	</div>
	<div id="brand-content " class="nav-content ">
		<div class="nav-con-close ">
			<i class="am-icon-angle-right am-icon-fw "></i>
		</div>
		<div>
			收藏
		</div>
	</div>
	<div id="broadcast-content " class="nav-content ">
		<div class="nav-con-close ">
			<i class="am-icon-angle-right am-icon-fw "></i>
		</div>
		<div>
			充值
		</div>
	</div>
</div>
		<script src="/js/vue.js"></script>
		<script src="/js/jquery-1.7.2.min.js"></script>
		<script src="/AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>
		<script type="text/javascript " src="/basic/js/quick_links.js "></script>
		<script src="/js/vue-lazyload.js"></script>
		<script type="text/javascript">
			(function() {
				$('.am-slider').flexslider();
			});
		</script>
		
		<script type="text/javascript">
		 if ($(window).width() < 640) {
		        function autoScroll(obj) {
		            $(obj).find("ul").animate({
		                marginTop: "-39px"
		            }, 500, function() {
		                $(this).css({
		                    marginTop: "0px"
		                }).find("li:first").appendTo(this);
		            })
		        }
		        $(function() {
		            setInterval('autoScroll(".demo")', 3000);
		        })
		    }
		</script>
		<script type="text/javascript">
		 
			var detail={};
			
			var List={};
			
			var parentCatalogArray = [];
			

			$(document).ready(function(){
				 $(window).scroll(function(){
			            scrollLoad();
			        });

			        Vue.use(VueLazyload, {
			            preLoad: 0.8,
			            loading: '/images/loading.gif',//这个是加载的loading过渡效果
			            try: 2  // 这个是加载图片数量
			        })
			        
				detail= new Vue({
					el : '#nav',
					data : {
						username : 'tom',
						List : []/*vue的使用数据以数组的形式保存   */
					},
					beforeCreate : function () {
						alert("beforeCreate--" + this.username + ', el--' + this.$el);
		            },
					created : function () {
		                alert("created--" + this.username + ', el--' + this.$el);
		                parentList(); /* 获取旁边的目录  */
		            },

					beforeMount : function() {
		                alert("beforeMount--" + this.username + ', el--' + this.$el);
					},

					mounted : function () {
		                alert("mounted--" + this.username + ', el--' + this.$el);
		            },

					beforeUpdate : function () {
		                alert("beforeUpdated--" + this.username + ', el--' + this.$el);
		            },
					updated : function () {

		                alert("updated--" + this.username + ', el--' + this.$el);
		                initCatalog();
		            }
		  
				});
				
				List= new Vue({
					el : '#books',
					data : {
						Flow : []/*vue的使用数据以数组的形式保存   */
					},
				});
				parent();
			})/*  vue的整个生命周期 这里在显示二级目录时非常重要 */
			
			  function scrollLoad() {
			        var top = $(window).scrollTop();//当前浏览器窗口所在文档的高度
			        var win = $(window).height();  //浏览器窗口可见区域的高度
			        var doc = $(document).height();//整个页面文档的高度
			        if ((top + win) >= doc) {
			        	Lazyload();
			        }
			    }
			        
			
			function initCatalog(){
				$("li").hover(function() {
					$(".category-content .category-list li.first .menu-in").css("display", "none");
					$(".category-content .category-list li.first").removeClass("hover");
					$(this).addClass("hover");
					$(this).children("div.menu-in").css("display", "block")
				}, function() {
					$(this).removeClass("hover")
					$(this).children("div.menu-in").css("display", "none")
				});
			}
			
				function parent(){
				
					$.post('/catalog/ParentList.do',{},Parentback);
				}
				
				function Parentback (ajaxDAO,status){
					if(ajaxDAO.success){
						parentCatalogArray=ajaxDAO.data;//这里获取的是一级目录 的数组
					}
				}
				 
				 function Lazyload(){
					if (parentCatalogArray.length == 0) {
					    return false;
					}
					
				 	var catalog=parentCatalogArray.shift();//这里每滑动一下鼠标调用一下该方法注意这里push是输出一个元素 
					var param={
							catalogNum:catalog.catalogNum
						};
					$.post('/catalog/Lazyload.do',param,Lazyback);
				} 
				
				function Lazyback(ajaxDAO,status){
					if(ajaxDAO.success){
						List.Flow.push(ajaxDAO.data);//输入
						}
					}
				
				function parentList(){
					
					$.post('/catalog/cataList.do',{},Callback);
				}
				
				function Callback(ajaxDAO,status){
					if(ajaxDAO.success){
						var result = ajaxDAO.data;
						detail.List = result;
					}
				}
			
			</script>
	</body>




</html>