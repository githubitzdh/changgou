<template>
<div>
  <body>
    <!-- 顶部导航 start -->
    <TopNav></TopNav>
    <!-- 顶部导航 end -->

    <div style="clear:both;"></div>

    <!-- 页面头部 start -->
    <div class="header w990 bc mt15">
      <div class="logo w990">
        <h2 class="fl">
          <a href="index.html">
            <img src="images/logo.png" alt="畅购商城" />
          </a>
        </h2>
        <div class="flow fr">
          <ul>
            <li class="cur">1.我的购物车</li>
            <li>2.填写核对订单信息</li>
            <li>3.成功提交订单</li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 页面头部 end -->

    <div style="clear:both;"></div>

    <!-- 主体部分 start -->
    <div class="mycart w990 mt10 bc">
      <h2>
        <span>我的购物车</span>
      </h2>
      <table>
        <thead>
          <tr>
            <th class="col0">
              <input type="checkbox" :checked="allChecked" @click="checkAll($event)" />全选
              <!-- <input type="checkbox" v-model="allChecked" />全选 -->
            </th>
            <th class="col1">商品名称</th>
            <th class="col2">商品信息</th>
            <th class="col3">单价</th>
            <th class="col4">数量</th>
            <th class="col5">小计</th>
            <th class="col6">操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- 购物车start -->
          <tr v-for="(item,index) in cart" :key="index">
            <td class="col0">
              <!-- 只能控制选中与否  :checked="item.checked"
              可以更新数据    v-model="item.checked"  -->
              <input type="checkbox" v-model="item.checked" />
            </td>

            <td class="col1">
              <a href>
                <img :src="item.midlogo" alt />
              </a>
              <strong>
                <a :href="item.skuid">{{item.goods_name}}</a>
              </strong>
            </td>
            <td class="col2">
              <p v-for="(value,key,index) in item.spec_info_id_txt" :key="index">{{key}} : {{value}}</p>
            </td>
            <td class="col3">
              ￥
              <span>{{item.price}}</span>
            </td>
            <td class="col4">
              <a href="javascript:;" class="reduce_num" @click.prevent="minus(item)"></a>
              <input
                type="text"
                name="amount"
                v-model="item.count"
                class="amount"
                @keyup.prevent="updateCount(item,$event)"
              />
              <a href="javascript:;" class="add_num" @click.prevent="plus(item)"></a>
              <!-- class="add_num" -->
            </td>
            <td class="col5">
              ￥
              <span>{{item.price * item.count}}</span>
            </td>
            <td class="col6">
              <a href @click.prevent="del(index)">删除</a>
            </td>
          </tr>
          <!-- 购物车end -->
        </tbody>
        <tfoot>
          <tr>
            <td colspan="7">
              购物金额总计：
              <strong>
                ￥
                <span id="total">{{totalPrice}}</span>
              </strong>
            </td>
          </tr>
        </tfoot>
      </table>
      <div class="cart_btn w990 bc mt10">
        <a href class="continue">继续购物</a>
        <a href="flow2.html" class="checkout" @click.prevent="submit">结 算</a>
      </div>
    </div>
    <!-- 主体部分 end -->
    <div style="clear:both;"></div>
    <!-- 底部版权 start -->
    <Footer></Footer>
    <!-- 底部版权 end -->
  </body>
</div>
</template>

<script>
import TopNav from "../components/TopNav";
import Footer from "../components/Footer";
export default {
  head: {
    title: "首页",
    link: [{ rel: "stylesheet", href: "/style/cart.css" }],
    script: [
      // { type: "text/javascript", src: "/js/cart1.js" }
      ]
  },
  components: {
    TopNav,
    Footer
  },
  data() {
    return {
      cart: [], //购物车中数据
      allChecked: false //全选状态
    };
  },
  async mounted() {
    //获得购物车信息(分类两种情况,未登录从浏览器获取,登录从ajax获取)
    //1.获得token
    let token = sessionStorage.getItem("token");
    if (token) {
      //2.1.登录 --ajax查询
      let { data } = await this.$request.selectCart();
      //获得购物车数据
      this.cart = data.data;
    } else {
      //2.2.未登录--localStorage
      let cartStr = localStorage.getItem("cart");
      this.cart = JSON.parse(cartStr);
    }
  },
  computed: {
    totalPrice: function() {
      //求和
      let sum = 0;
      //遍历累加,求所有小计和
      this.cart.forEach(item => {
        sum += item.price * item.count;
      });
      return sum;
    }
  },
  methods: {
    minus(goods) {
      //减操作
      if (goods.count > 1) {
        goods.count--;
      }
    },
    plus(goods) {
      //加操作
      goods.count++;
    },
    updateCount(goods, e) {
      console.info(e.target.value);
      if (/^\d+$/.test(e.target.value)) {
        goods.count = e.target.value;
      } else {
        goods.count = 1;
      }
    },
    checkAll(e) {
    
      //获得全选复选框的状态,可以控制购物车中购物项的状态
      this.cart.forEach(item => {
        item.checked = e.target.checked;
      });
        console.info(this.allChecked)
    },
    async del(index) {
      //参数1;删除开始位置
      //参数2;删除个数
      if (confirm("您确定要删除吗？")) {
        this.cart.splice(index, 1);
      }
    },
    submit(){
      //如果登录跳转flow2,否则跳转到登录页面,同时记录需要跳转的页面路径
      //1.获得token
      let token=sessionStorage.getItem('token')
      if(token){
      //2.登录跳转flow2
      this.$router.push('/flow2')
      }else{
        //3.未登录,跳转到登录页面
        sessionStorage.setItem('returnURL',"/flow2")
       this.$router.push('/login')
      }
    }
  },
  watch: {
    //深度监听
    cart: {
      async handler(val, oldCart) {
        //1 根据登录情况,更新购物车
        let token = sessionStorage.getItem("token");
        if (token) {
          //1.1登录
          //登录,发送ajax更新数据
          let { data } = await this.$request.updateCart(val);
          if (data.code == 0) {
            alert(data.message);
          }
        } else {
          //1.2 未登录-将购物车中数据保存到localStorage中
          //JSON.stringify 对象转为字符串
          localStorage.setItem("cart", JSON.stringify(val));
        }
         //2.购物项选中个数,购物车个数相同,全选需要选中
        //2 处理全选
        let cartSize = this.cart.length;
       let checkedSize=this.cart.filter(item=>item.checked).length;
       this.allChecked=cartSize==checkedSize;
    },
      immediate: false, //true代表如果在 watch 里声明了之后，就会立即先去执行里面的handler方法
      deep: true //深度监听，常用于对象下面属性的改变
    },
  
  }
};
</script>

<style>
</style>