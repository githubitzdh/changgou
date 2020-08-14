<template>
  <div>
    <!-- 顶部导航 start -->
    <TopNav></TopNav>
    <!-- 顶部导航 end -->

    <div style="clear:both;"></div>

    <!-- 头部 start -->
    <HeaderSearch :isFirst="false" :list="categoryList"></HeaderSearch>
    <!-- 头部 end-->

    <div style="clear:both;"></div>

    <!-- 列表主体 start -->
    <div class="list w1210 bc mt10">
      <!-- 面包屑导航 start -->
      <div class="breadcrumb">
        <h2>
          当前位置：
          <a href>首页</a> >
          <a href>电脑、办公</a>
        </h2>
      </div>
      <!-- 面包屑导航 end -->

      <!-- 左侧内容 start -->
      <div class="list_left fl mt10">
        <!-- 分类列表 start -->
        <div class="catlist">
          <h2>电脑、办公</h2>
          <div class="catlist_wrap">
            <div class="child">
              <h3 class="on">
                <b></b>电脑整机
              </h3>
              <ul>
                <li>
                  <a href>笔记本</a>
                </li>
                <li>
                  <a href>超极本</a>
                </li>
                <li>
                  <a href>平板电脑</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>电脑配件
              </h3>
              <ul class="none">
                <li>
                  <a href>CPU</a>
                </li>
                <li>
                  <a href>主板</a>
                </li>
                <li>
                  <a href>显卡</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>办公打印
              </h3>
              <ul class="none">
                <li>
                  <a href>打印机</a>
                </li>
                <li>
                  <a href>一体机</a>
                </li>
                <li>
                  <a href>投影机</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>网络产品
              </h3>
              <ul class="none">
                <li>
                  <a href>路由器</a>
                </li>
                <li>
                  <a href>网卡</a>
                </li>
                <li>
                  <a href>交换机</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>外设产品
              </h3>
              <ul class="none">
                <li>
                  <a href>鼠标</a>
                </li>
                <li>
                  <a href>键盘</a>
                </li>
                <li>
                  <a href>U盘</a>
                </li>
              </ul>
            </div>
          </div>

          <div style="clear:both; height:1px;"></div>
        </div>
        <!-- 分类列表 end -->

        <div style="clear:both;"></div>

        <!-- 新品推荐 start -->
        <div class="newgoods leftbar mt10">
          <h2>
            <strong>新品推荐</strong>
          </h2>
          <div class="leftbar_wrap">
            <ul>
              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot1.jpg" alt />
                    </a>
                  </dt>
                  <dd>
                    <a href>美即流金丝语悦白美颜新年装4送3</a>
                  </dd>
                  <dd>
                    <strong>￥777.50</strong>
                  </dd>
                </dl>
              </li>

              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot2.jpg" alt />
                    </a>
                  </dt>
                  <dd>
                    <a href>领券满399减50 金斯利安多维片</a>
                  </dd>
                  <dd>
                    <strong>￥239.00</strong>
                  </dd>
                </dl>
              </li>

              <li class="last">
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot3.jpg" alt />
                    </a>
                  </dt>
                  <dd>
                    <a href>皮尔卡丹pierrecardin 男士长...</a>
                  </dd>
                  <dd>
                    <strong>￥1240.50</strong>
                  </dd>
                </dl>
              </li>
            </ul>
          </div>
        </div>
        <!-- 新品推荐 end -->

        <!--热销排行 start -->
        <div class="hotgoods leftbar mt10">
          <h2>
            <strong>热销排行榜</strong>
          </h2>
          <div class="leftbar_wrap">
            <ul>
              <li></li>
            </ul>
          </div>
        </div>
        <!--热销排行 end -->

        <!-- 最近浏览 start -->
        <div class="viewd leftbar mt10">
          <h2>
            <a href>清空</a>
            <strong>最近浏览过的商品</strong>
          </h2>
          <div class="leftbar_wrap">
            <dl>
              <dt>
                <a href>
                  <img src="/images/hpG4.jpg" alt />
                </a>
              </dt>
              <dd>
                <a href>惠普G4-1332TX 14英寸笔记...</a>
              </dd>
            </dl>

            <dl class="last">
              <dt>
                <a href>
                  <img src="/images/crazy4.jpg" alt />
                </a>
              </dt>
              <dd>
                <a href>直降200元！TCL正1.5匹空调</a>
              </dd>
            </dl>
          </div>
        </div>
        <!-- 最近浏览 end -->
      </div>
      <!-- 左侧内容 end -->

      <!-- 列表内容 start -->
      <div class="list_bd fl ml10 mt10">
        <!-- 热卖、促销 start -->
        <div class="list_top">
          <!-- 热卖推荐 start -->
          <div class="hotsale fl">
            <h2>
              <strong>
                <span class="none">热卖推荐</span>
              </strong>
            </h2>
            <ul>
              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/hpG4.jpg" alt />
                    </a>
                  </dt>
                  <dd class="name">
                    <a href>惠普G4-1332TX 14英寸笔记本电脑 （i5-2450M 2G 5</a>
                  </dd>
                  <dd class="price">
                    特价：
                    <strong>￥2999.00</strong>
                  </dd>
                  <dd class="buy">
                    <span>立即抢购</span>
                  </dd>
                </dl>
              </li>

              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot3.jpg" alt />
                    </a>
                  </dt>
                  <dd class="name">
                    <a href>ThinkPad E42014英寸笔记本电脑</a>
                  </dd>
                  <dd class="price">
                    特价：
                    <strong>￥4199.00</strong>
                  </dd>
                  <dd class="buy">
                    <span>立即抢购</span>
                  </dd>
                </dl>
              </li>

              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/acer4739.jpg" alt />
                    </a>
                  </dt>
                  <dd class="name">
                    <a href>宏碁AS4739-382G32Mnkk 14英寸笔记本电脑</a>
                  </dd>
                  <dd class="price">
                    特价：
                    <strong>￥2799.00</strong>
                  </dd>
                  <dd class="buy">
                    <span>立即抢购</span>
                  </dd>
                </dl>
              </li>
            </ul>
          </div>
          <!-- 热卖推荐 end -->

          <!-- 促销活动 start -->
          <div class="promote fl">
            <h2>
              <strong>
                <span class="none">促销活动</span>
              </strong>
            </h2>
            <ul>
              <li>
                <b>.</b>
                <a href>DIY装机之向雷锋同志学习！</a>
              </li>
              <li>
                <b>.</b>
                <a href>京东宏碁联合促销送好礼！</a>
              </li>
              <li>
                <b>.</b>
                <a href>台式机笔记本三月巨惠！</a>
              </li>
              <li>
                <b>.</b>
                <a href>富勒A53g智能人手识别鼠标</a>
              </li>
              <li>
                <b>.</b>
                <a href>希捷硬盘白色情人节专场</a>
              </li>
            </ul>
          </div>
          <!-- 促销活动 end -->
        </div>
        <!-- 热卖、促销 end -->

        <div style="clear:both;"></div>

        <!-- 商品筛选 start -->
        <div class="filter mt10">
          <h2>
            <a href>重置筛选条件</a>
            <strong>商品筛选</strong>
          </h2>
          <div class="filter_wrap">
            <!-- 品牌start -->
            <dl>
              <dt>品牌：</dt>
              <!-- <dd class="cur"> 是否选中 -->
              <dd :class="{'cur':searchMap.brandId==''}">
                <a href @click.prevent="brandSearch('')">不限</a>
              </dd>
              <dd
                :class="{'cur':searchMap.brandId ==brand.id}"
                v-for="(brand,index) in brandList"
                :key="index"
              >
                <a href @click.prevent="brandSearch(brand.id)">{{brand.brandName}}</a>
              </dd>
              <!-- 品牌end -->
            </dl>

            <!-- 规格start -->
            <!--    <dl>
              <dt>价格：</dt>
              <dd class="cur">
                <a href>不限</a>
              </dd>
              <dd>
                <a href>1000-1999</a>
              </dd>
            </dl>-->

            <dl v-for="(spec,specindex) in specList" :key="specindex">
              <dt>{{spec.specName}} ：</dt>
              <dd class="cur">
                <a href @click.prevent="specSearch(spec.specName,null,$event)">不限</a>
              </dd>
              <dd v-for="(opt,optindex) in spec.options" :key="optindex">
                <a
                  href
                  @click.prevent="specSearch(spec.specName,opt.optionName,$event)"
                >{{opt.optionName}}</a>
              </dd>
            </dl>

            <!-- 规格end -->
          </div>
        </div>
        <!-- 商品筛选 end -->

        <div style="clear:both;"></div>

        <!-- 排序 start -->
        <div class="sort mt10">
          <dl>
            <dt>排序：</dt>
            <dd :class="{'cur':this.searchMap.sortBy=='xl'}">
              <a href @click.prevent="sortSearch('xl')">
                销量
                <!-- 升序:销量由低到高 -->
                <span v-if="searchMap.sortBy=='xl' &&searchMap.sortWay=='asc'">↑</span>
                <!-- 降序:销量由高到低 -->
                <span v-if="searchMap.sortBy=='xl'&&searchMap.sortWay=='desc'">↓</span>
              </a>
            </dd>
            <dd :class="{'cur':this.searchMap.sortBy=='jg'}">
              <a href @click.prevent="sortSearch('jg')">
                价格
                <!-- 升序:价格由低到高 -->
                <span v-if="searchMap.sortBy=='jg' &&searchMap.sortWay=='asc'">↑</span>
                <!-- 降序:价格由高到低 -->
                <span v-if="searchMap.sortBy=='jg'&&searchMap.sortWay=='desc'">↓</span>
              </a>
            </dd>

            <dd :class="{'cur':this.searchMap.sortBy=='pl'}">
              <a href @click.prevent="sortSearch('pl')">
                评论数
                <!-- 升序:评论数由低到高 -->
                <span v-if="searchMap.sortBy=='pl' &&searchMap.sortWay=='asc'">↑</span>
                <!-- 降序:评论数由高到低 -->
                <span v-if="searchMap.sortBy=='pl'&&searchMap.sortWay=='desc'">↓</span>
              </a>
            </dd>
            <dd :class="{'cur':this.searchMap.sortBy=='sj'}">
              <a href @click.prevent="sortSearch('sj')">
                上架时间
                <!-- 升序:上架时间由低到高 -->
                <span v-if="searchMap.sortBy=='sj' &&searchMap.sortWay=='asc'">↑</span>
                <!-- 降序:上架时间由高到低 -->
                <span v-if="searchMap.sortBy=='sj'&&searchMap.sortWay=='desc'">↓</span>
              </a>
            </dd>
          </dl>价格:
          <input type="text" v-model="searchMap.minPrice" style="width:80px" placeholder="￥" />-
          <input type="text" v-model="searchMap.maxPrice" style="width:80px" placeholder="￥" />
          <input type="button" value="搜索" @click.prevent="searchList" />
        </div>
        <!-- 排序 end -->

        <div style="clear:both;"></div>

        <!-- 商品列表 start-->
        <div class="goodslist mt10">
          <ul>
            <li v-for="(sku,index) in searchResult.list" :key="index" style="height:280px">
              <dl>
                <dt>
                  <a href>
                    <img :src="sku.midlogo" alt />
                  </a>
                </dt>
                <dd>
                  <a :href="'/Goods?id='+sku.id">{{sku.goodsName}}</a>
                </dd>
                <dd>
                  <strong>￥{{sku.price}}</strong>
                </dd>
                <dd>
                  <a href>
                    <em>已有{{sku.commentCount}} 人评价</em>
                  </a>
                </dd>
              </dl>
            </li>
          </ul>
        </div>
        <!-- 商品列表 end-->

        <!-- 分页信息 start -->
        <Pagination
          :total="searchResult.count"
          :page_size="searchMap.pageSize"
          @page_changed="pageChanged"
        ></Pagination>
        <!-- 分页信息 end -->
      </div>
      <!-- 列表内容 end -->
    </div>
    <!-- 列表主体 end-->

    <div style="clear:both;"></div>
    <!-- 底部导航 start -->
    <BottomNav></BottomNav>
    <!-- 底部导航 end -->

    <div style="clear:both;"></div>
    <!-- 底部版权 start -->
    <Footer></Footer>
    <!-- 底部版权 end -->
  </div>
</template>

<script>
//导入自定义组件
import TopNav from "~/components/TopNav";
import Footer from "~/components/Footer";
import HeaderSearch from "~/components/HeaderSearch";
import BottomNav from "~/components/BottomNav";
import Pagination from "~/components/Pagination";
export default {
  head: {
    title: "搜索页",
    link: [
      { rel: "stylesheet", href: "/style/list.css" },
      { rel: "stylesheet", href: "/style/common.css" },
      { rel: "stylesheet", href: "/style/bottomnav.css" }
    ],
    script: [
      { type: "text/javascript", src: "/js/header.js" },
      { type: "text/javascript", src: "/js/list.js" }
    ]
  },
  components: {
    //声明自定义
    TopNav,
    Footer,
    HeaderSearch,
    BottomNav,
    Pagination //分页组件
  },
  async asyncData({ app }) {
    //多次请求(只有一次)
    let [{ data: categoryData }] = await Promise.all([
      app.$request.findCategorys()
    ]);
    return {
      categoryList: categoryData.data
    };
  },
  data() {
    return {
      specList: [], //所有规格
      brandList: [], //所有品牌
      searchMap: {
        //搜索条件
        brandId: "", //当前品牌id
        catId: "", //当前分类id
        pageNum: 1, //分页
        pageSize: 2,
        specList: {}, //规格选项条件
        keyword: "", //关键字
        sortBy: "xl", //排序字段
        sortWay: "asc", //排序方式
        minPrice: "", //价格区间最小值
        maxPrice: "" //价格区间最大值
      },
      searchResult: {} //查询结果
    };
  },
  async mounted() {
    //页面加载成功
    //1)获得分类id
    this.searchMap.catId = this.$route.params.cid;
    //2)查询品牌,并保存结果
    let { data: b } = await this.$request.findBrand(this.searchMap.catId);
    this.brandList = b.data;

    //3)查询规格,并保存结果
    let { data: sp } = await this.$request.findSpec(this.searchMap.catId);
    this.specList = sp.data;

    //4)搜索
    this.searchList();
  },
  methods: {
    brandSearch(bid) {
      //切换品牌
      this.searchMap.brandId = bid;
      //查询
      this.searchList();
    },
    specSearch(specName, optionName, e) {
      //参数1:specName 规格名称
      //参数2:optionName 规格选项条件

      //处理条件:选项值:R如果存在添加条件,如果不存在删除条件
      if (optionName) {
        //存在
        this.searchMap.specList[specName] = optionName;
      } else {
        //不存在
        delete this.searchMap.specList[specName];
      }
      //重新查询
      this.searchList();

      //所点击的添加cur样式
      $(e.target)
        .parent()
        .addClass("cur");
      //给点击除外的兄弟移除cur样式
      $(e.target)
        .parent()
        .siblings()
        .removeClass("cur");
    },
    async searchList() {
      //ajax查询
      let { data } = await this.$request.search(this.searchMap);
      //保存结果
      this.searchResult = data.data;
    },
    sortSearch(sortBy) {
      //第一次点击,之后点击切换排序方式
      if (this.searchMap.sortBy == sortBy) {
        //点击第二次点击切换排序方式
        this.searchMap.sortWay =
          this.searchMap.sortWay == "asc" ? "desc" : "asc";
      } else {
        //第一次点击默认升序
        this.searchMap.sortBy = sortBy;
        this.searchMap.sortWay = "asc";
      }
      //查询
      this.searchList();
    },
    pageChanged(pageNum) {
      console.info(pageNum);
      //重新设置第几页
      this.searchMap.pageNum = pageNum;
      //查询
      this.searchList();
    }
  },
  watch: {
    "$store.state.keyword": function(newValue, oldValue) {
      //添加keyword查询条件
      this.searchMap.keyword = newValue;
      //查询
      this.searchList();
    }
  }
};
</script>

<style>
</style>