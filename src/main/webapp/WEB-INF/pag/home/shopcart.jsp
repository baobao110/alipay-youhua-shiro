<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>购物车页面</title>

		<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="../basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="../css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="../css/optstyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="../js/jquery.js"></script>

	</head>

	<body>
	<div id="cart">
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
				<div class="logo"><img src="../images/logo.png" /></div>
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

			<!--购物车 -->
			<div class="concent" >
				<div id="cartTable">
					<div class="cart-table-th">
						<div class="wp">
							<div class="th th-chk">
								<div id="J_SelectAll1" class="select-all J_SelectAll">

								</div>
							</div>
							<div class="th th-item">
								<div class="td-inner">商品信息</div>
							</div>
							<div class="th th-price">
								<div class="td-inner">单价</div>
							</div>
							<div class="th th-amount">
								<div class="td-inner">数量</div>
							</div>
							<div class="th th-sum">
								<div class="td-inner">金额</div>
							</div>
							<div class="th th-op">
								<div class="td-inner">操作</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>

					<tr class="item-list">
						<div class="bundle  bundle-last ">
							<div class="bundle-hd">
								<div class="bd-promos">
									<div class="bd-has-promo">已享优惠:<span class="bd-has-promo-content">省￥19.50</span>&nbsp;&nbsp;</div>
									<div class="act-promo">
										<a href="#" target="_blank">第二支半价，第三支免费<span class="gt">&gt;&gt;</span></a>
									</div>
									<span class="list-change theme-login">编辑</span>
								</div>
							</div>
							<div class="clear"></div>
							
							<div class="bundle-main">
								<div v-for="(listx,index) in list" >
								<ul class="item-content clearfix" >
									<li class="td td-chk">
										<div class="cart-checkbox ">
											<input class="check" v-bind:value="index" type="checkbox" v-model="checkedList"><!--注意这里的复选框的绑定根据index用 v-bind https://cn.vuejs.org/v2/guide/forms.html#复选框  触发updated方法,将index作为值保存到数组中 -->
											<label for="J_CheckBox_170037950254"></label>
										</div>
									</li>
									<li class="td td-item">
										<div class="item-pic">
											<a href="#" target="_v-bind:blank" v-bind:data-title="listx.bookName" class="J_MakePoint" data-point="tbcart.8.12">
												<img  v-bind:src="'http://192.168.111.128/'+listx.pictureUName" class="itempic J_ItemImg"></a>
										</div>
										<div class="item-info">
											<div class="item-basic-info">
												<a href="#" target="_blank" v-bind:title="listx.bookName" class="item-title J_MakePoint" data-point="tbcart.8.11">{{listx.bookName}}</a>
											</div>
										</div>
									</li>
									<li class="td td-price">
										<div class="item-price price-promo-promo">
											<div class="price-content">
												<div class="price-line">
													<em class="price-original">{{listx.bookPrice}}</em>
												</div>
												<div class="price-line">
													<em class="J_Price price-now" tabindex="0">{{listx.bookPrice}}</em>
												</div>
											</div>
										</div>
									</li>
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<div class="item-amount ">
												<div class="sl">
													<input class="min am-btn" name="" type="button"  v-on:click="sub(index); " value="-" /><!--如果这里的加减方法不传入index就是整体的加减,传入index才知道对哪一个商品具体的加减  -->
													<input class="text_box" name="" type="text" v-model="listx.num" style="width:30px;"/><!-- 注意这里的v-model用法 输入动态绑定 https://cn.vuejs.org/v2/guide/forms.html -->
													<input class="add am-btn" name="" type="button"  v-on:click="add(index); " value="+" />
													<input class="text_box" name="" type="text" v-model="listx.price" style="width:30px;" value="listx.price"/> 
												</div>
											</div>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
										</div>
									</li>
									<li class="td td-op">
										<div class="td-inner">
											<a title="移入收藏夹" class="btn-fav" href="#">
                  移入收藏夹</a>
											<a href="javascript:;" v-on:click="dele(index)" data-point-url="#" class="delete">
                  删除</a>
										</div>
									</li>
									</div>
								</ul>
					</tr>
					<div class="clear"></div>


				<div class="float-bar-wrapper">
					<div id="J_SelectAll2" class="select-all J_SelectAll">
						<div class="cart-checkbox">
							<input class="check-all check" v-model="toggle" true-value="yes" false-value="no" v-on:click="All" type="checkbox"><!-- https://cn.vuejs.org/v2/guide/forms.html#复选框-1,注意这里的v-on:click  -->
							<label for="J_SelectAllCbx2"></label>
						</div>
						<span>全选</span>
					</div>
					<div class="operations">
						<a href="#" hidefocus="true" class="deleteAll">删除</a>
						<a href="#" hidefocus="true" class="J_BatchFav">移入收藏夹</a>
					</div>
					<div class="float-bar-right">
						<div class="amount-sum">
							<span class="txt">已选商品:</span>
							<em id="J_SelectedItemsCount">{{checkedList.length}}</em><span class="txt">件</span>
							<div class="arrow-box">
								<span class="selected-items-arrow"></span>
								<span class="arrow"></span>
							</div>
						</div>
						<div class="price-sum">
							<span class="txt">合计:</span>
							<strong class="price">¥<em id="J_Total">{{total}}</em></strong>
						</div>
						<div class="btn-area">
							<a href="javascript:pay(); " id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
								<span>结&nbsp;算</span></a>
						</div>
					</div>

				 </div>
			</div>
		</div>

				<div class="footer">
					<div class="footer-hd">
						<p>
							<a href="#">恒望科技</a>
							<b>|</b>
							<a href="#">商城首页</a>
							<b>|</b>
							<a href="#">支付宝</a>
							<b>|</b>
							<a href="#">物流</a>
						</p>
					</div>
					<div class="footer-bd">
						<p>
							<a href="#">关于恒望</a>
							<a href="#">合作伙伴</a>
							<a href="#">联系我们</a>
							<a href="#">网站地图</a>
							<em>© 2015-2025 Hengwang.com 版权所有</em>
						</p>
					</div>
				</div>
			
			</div>
		</div>

			<!--操作页面-->

			<div class="theme-popover-mask"></div>

			<div class="theme-popover">
				<div class="theme-span"></div>
				<div class="theme-poptit h-title">
					<a href="javascript:;" title="关闭" class="close">×</a>
				</div>
				<div class="theme-popbod dform">
					<form class="theme-signin" name="loginform" action="" method="post">

						<div class="theme-signin-left">

							<li class="theme-options">
								<div class="cart-title">颜色：</div>
								<ul>
									<li class="sku-line selected">12#川南玛瑙<i></i></li>
									<li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
								</ul>
							</li>
							<li class="theme-options">
								<div class="cart-title">包装：</div>
								<ul>
									<li class="sku-line selected">包装：裸装<i></i></li>
									<li class="sku-line">两支手袋装（送彩带）<i></i></li>
								</ul>
							</li>
							<div class="theme-options">
								<div class="cart-title number">数量</div>
								<dd>
									<input class="min am-btn am-btn-default" name="" type="button" value="-" />
									<input class="text_box" name="" type="text" value="1" style="width:30px;" />
									<input class="add am-btn am-btn-default" name="" type="button" value="+" />
									<span  class="tb-hidden">库存<span class="stock">1000</span>件</span>
								</dd>

							</div>
							<div class="clear"></div>
							<div class="btn-op">
								<div class="btn am-btn am-btn-warning">确认</div>
								<div class="btn close am-btn am-btn-warning">取消</div>
							</div>

						</div>
						<div class="theme-signin-right">
							<div class="img-info">
								<img src="../images/kouhong.jpg_80x80.jpg" />
							</div>
							<div class="text-info">
								<span class="J_Price price-now">¥39.00</span>
								<span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
							</div>
						</div>

					</form>
				</div>
			</div>
		<!--引导 -->
		<div class="navCir">
			<li><a href="home2.html"><i class="am-icon-home "></i>首页</a></li>
			<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
			<li class="active"><a href="shopcart.html"><i class="am-icon-shopping-basket"></i>购物车</a></li>	
			<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>					
		</div>
	</div>
		
		<script type="text/javascript" src="/basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="/basic/js/quick_links.js"></script>
		<script type="text/javascript" src="/js/jquery.imagezoom.min.js"></script>
		<script type="text/javascript" src="/AmazeUI-2.4.2/assets/js/amazeui.js"></script>

		<script type="text/javascript" src="/js/jquery.flexslider.js"></script>
		<script src="/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="/js/vue.js"></script>
		

	<script>
	
		var cart={};
		
		$(document).ready(function(){
			 cart= new Vue({
					el : '#cart',/* 作用域范围 Vue */
					data : {
						 list: [],
						 toggle : 'no',
						 checkedList:[],/*注意这里初始为空数组,每添加一个将序号保存具体用法见官网,如果去除会在 在数组中自动删除 ,这个数组单独存放被选中的商品 的数据 这里特别注意 这里的数据和List联系在一起的 所以只要在List中删除元素 List的数据根据数组的序号保存的 所以被删信息自然不会存在于 checkedList中  */
						 total:0
					},
					
					created : function () {
						detail(); /* created就是初次加载时就直接调用的方法 */
                    },
                    
                    updated : function () {
                    	  var all = 0;
                    	  for (var i=0;i<this.checkedList.length;i++) {
                              var index = this.checkedList[i];
                              var chosedCom = this.list[index];
                              var commoTotal = chosedCom.price;
                              all += commoTotal;
                          }
                    	 /*  $('#J_Total').html(all); */ 
                    	 this.total=all.toFixed(2); /*updated方法就是在页面点击任意时间页面内容变化时自动调用  ,这里每当有事件触发 就会将选中列表改变 ,js前端四舍五入保存小数点后两位   */
                    	  
                    },
                    
					methods : {
						dele:function(index){
							del(index);
						},
						add : function(index){
							/* alert(parseInt(index)); */
							if(this.list[index].stock<= this.list[index].num){
								 return false;
							}
							 this.list[index].num++;
							 this.list[index].price=this.list[index].num*this.list[index].bookPrice;
							 /* total(); */ 
						},
					
						sub:function(index){
							/* alert(parseInt(index)); */
							if( this.list[index].num<=0){
								 return false;
							}
							 this.list[index].num--;
							 this.list[index].price=this.list[index].num*this.list[index].bookPrice;
							/*   total();  */
						},
						All:function(){
							this.checkedList=[];/*全选时先设置 数组为空将原来选择的清空  */
							if(this.toggle==='no'){
								 for (var i=0;i<this.list.length;i++) {
	                                    this.checkedList.push(i);/* 注意这里的全选 用法  ,这里就是专门的一个数组存放被选中的商品index  这里为什么保存index为了后面的实时显示结算金额见updated方法,同时也是保存最后要结算的商品集合序号 */
	                                }
								/*  total(); */
							}
						}
					}
		  
				});
				
			});
		
		function detail(){
			$.post('/cart/detail.do',{},detailCallback);
		}
		
		function detailCallback(ajaxDAO,status){
			if(ajaxDAO.success){
				cart.list = ajaxDAO.data.dto;
			}
			
		}
		
		function del(index){
			var del=cart.list[index];
			var param={
					id:del.id,
					index:index
				};
			$.post('/cart/delete.do',param,deleteCallback);
			cart.list.splice(index, 1);//这就是将原来中的列表中的相应的删除 ,这里为什么只删除list中的数据是因为checkedList中保存的是list的数组序号,所以List中的元素删除 checkedList中国自然没有被删信息 
			 
		}
		
		function deleteCallback(ajaxDAO,status){
			if(ajaxDAO.success){
				alert("删除成功");	
			}
		}
		
		/* function total(){
			var all=0;
			for(var i=0;i<cart.list.length;i++){
				 all+=cart.list[i].price; 
			};
			$('#J_Total').html(all); 
		} */
		
		function pay(){
			var array=" ";
			for(var i=0;i<cart.checkedList.length;i++){
				var j=cart.checkedList[i];
				var v=cart.list[j].id+","+cart.list[j].num+";";
				array+=v;
			};
			
			alert(array);
			  $.ajax({
                  url: '/cart/result.do',
                  type: 'POST', //GET
                  async: true,    //或false,是否异步
                  contentType:"application/json",
                  data:array,
                  timeout: 5000,    //超时时间
                  dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                  success: function (ajaxDAO, Status) {
                      if (!ajaxDAO.success) {
                          alert(ajaxDAO.message);
                          return;
                      }

                      window.location.href = '/Page/toPay.do';
                  }
              });
		
		}/* 这里注意 ajax的传递形式 ,这里的  dataType就是定义传递数据的 发送形式 ,这里可以联系 jsonp的跨域 问题,就是在  dataType中定义为jsonp ,这里从购物车跳转到支付页面时已字符串的形式传递到后台 将相应的商品在购物车数据库状态码改为2,到支付页面只需要根据id和 status选中商品 */
		
		function finalCallback(ajaxDAO,status){
			if(ajaxDAO.success){
				window.location.href = '/Page/toPay.do';
			}
			else{
				alert("失败 ");
			}
			
		}
	</script>
	</body>

</html>