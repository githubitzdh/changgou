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
        <div class="flow fr flow3">
          <ul>
            <li>1.我的购物车</li>
            <li>2.填写核对订单信息</li>
            <li class="cur">3.成功提交订单</li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 页面头部 end -->

    <div style="clear:both;"></div>

    <!-- 主体部分 start -->
    <div class="success w990 bc mt15">
      <div class="success_hd">
        <h2>订单提交成功</h2>
      </div>
      <div class="success_bd">
        <p>
          <span></span>订单提交成功，我们将及时为您处理
        </p>
        <div id="qrcode"></div>
        <p class="message">
          完成支付后，你可以
          <a href @click.prevent="findPayStatusFn">查看订单状态</a>
          <a href>继续购物</a>
          <a href>问题反馈</a>
        </p>
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
    link: [{ rel: "stylesheet", href: "/style/success.css" }],
    script: [
      { type: "text/javascript", src: "/js/qrcode.min.js" },
      { type: "text/javascript", src: "/js/stomp.js" }
    ]
  },
  components: {
    TopNav,
    Footer
  },
  data() {
    return {
      sn: this.$route.query.sn
    };
  },
  async mounted() {
    //获得支付路径
    let { data } = await this.$request.pay(this.sn);
    //处理路径
    if (data.other.wxurl) {
      //获得成功,渲染二维码
      console.info(data.other.wxurl);
      //核心类
      let qrcode = new QRCode(document.getElementById("qrcode"), {
        width: 200,
        height: 200
      });
      //2.设置数据
      qrcode.makeCode(data.other.wxurl);
    } else {
      //有错误,含已支付情况，可以进行对应的数据处理
      alert("重新获得");
    }
    //建立stomp推送会话,进行数据监控,完成自动跳转
    this.autoChange();
  },
  methods: {
    async findPayStatusFn() {
      //发送ajax查询
      let { data } = await this.$request.findPayStatus(this.sn);
      //判断处理
      if (data.code == 1) {
        //跳转flow4页面
        location.href = "flow4";
      } else {
        //返回错误信息
        alert(data.message);
      }
    },
    autoChange() {
      //1.与RabbitMQ连接
      var client = Stomp.client("ws://localhost:15674/ws");

      // 关闭调试
      client.debug = false;

      //2
      //2.1成功回调函数
      var on_connect = (x) => {
        //订阅:RabbitMQ消息有多种类型:queue队列
        id = client.subscribe("/queue/order_pay_auto", (d)=> {
          let sn = d.body;
          if (sn == this.sn) {
            //跳转页面
            location.href = "flow4";
          }
        });
      };

      //2.2失败回调函数
      var on_error = function() {
        console.log("error");
      };

      //2.3获得连接  client.connect('用户名','密码',成功回调函数,失败回调函数,host)
      client.connect("guest", "guest", on_connect, on_error, "/");
    }
  }
};
</script>

<style>
#qrcode img {
  background-color: #fff;
  padding: 5px;    	/*内边距*/
  margin: 0 auto;      	/*居中*/
}
</style>