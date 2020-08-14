<template>
  <div>
    <!-- 顶部导航 start -->
    <TopNav></TopNav>
    <!-- 顶部导航 end -->
    <div style="clear:both;"></div>

    <!-- 页面头部 start -->
    <HeaderLogo></HeaderLogo>
    <!-- 页面头部 end -->

    <div style="clear:both;"></div>
    <!-- 正文 -->

    <!-- 登录主体部分start -->
    <div class="login w990 bc mt10">
      <div class="login_hd">
        <h2>用户登录</h2>
        <b></b>
      </div>
      <div class="login_bd">
        <div class="login_form fl">
          <form action method="post">
            <ul>
              <li>
                <label for>用户名：</label>
                <input
                  type="text"
                  class="txt"
                  @blur="changImg"
                  name="username"
                  v-model="user.username"
                />
              </li>
              <li>
                <label for>密码：</label>
                <input type="password" class="txt" name="password" v-model="user.password" />
                <a href>忘记密码?</a>
              </li>
              <li class="checkcode">
                <label for>验证码：</label>
                <input type="text" name="checkcode" v-model="user.code" />

                <img :src="imgUrl" alt />
                <span>
                  看不清？
                  <a href @click.prevent="changImg">换一张</a>
                </span>

                <p class="error">{{errorMsg}}</p>
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="checkbox" class="chb" /> 保存登录信息
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="submit" value class="login_btn" @click.prevent="loginFn" />
              </li>
            </ul>
          </form>

          <div class="coagent mt15">
            <dl>
              <dt>使用合作网站登录商城：</dt>
              <dd class="qq">
                <a href>
                  <span></span>QQ
                </a>
              </dd>
              <dd class="weibo">
                <a href>
                  <span></span>新浪微博
                </a>
              </dd>
              <dd class="yi">
                <a href>
                  <span></span>网易
                </a>
              </dd>
              <dd class="renren">
                <a href>
                  <span></span>人人
                </a>
              </dd>
              <dd class="qihu">
                <a href>
                  <span></span>奇虎360
                </a>
              </dd>
              <dd class>
                <a href>
                  <span></span>百度
                </a>
              </dd>
              <dd class="douban">
                <a href>
                  <span></span>豆瓣
                </a>
              </dd>
            </dl>
          </div>
        </div>

        <div class="guide fl">
          <h3>还不是商城用户</h3>
          <p>现在免费注册成为商城用户，便能立刻享受便宜又放心的购物乐趣，心动不如行动，赶紧加入吧!</p>

          <a href="regist.html" class="reg_btn">免费注册 >></a>
        </div>
      </div>
    </div>
    <!-- 登录主体部分end -->
    <div style="clear:both;"></div>
    <!-- 底部版权 start -->
    <Footer></Footer>
    <!-- 底部版权 end -->
  </div>
</template>

<script>
import TopNav from "../components/TopNav";
import HeaderLogo from "../components/HeaderLogo";
import Footer from "../components/Footer";

//导入vuex组件
import { mapMutations } from "vuex";
export default {
  head: {
    title: "用户登录",
    link: [{ rel: "stylesheet", href: "style/login.css" }],
    script: []
  },
  components: {
    TopNav,
    HeaderLogo,
    Footer
  },
  data() {
    return {
      imgUrl: "",
      user: {
        username: "",
        password: "",
        code: "" //验证码
      },
      errorMsg: "" //错误提示信息
    };
  },
  methods: {
    ...mapMutations(["setData"]),
    changImg() {
      //必须有用户名
      //修改图片路径
      this.imgUrl = `http://localhost:10010/v3/cgwebservice/verifycode?username=${
        this.user.username
      }&t=${new Date().getTime()}`;
    },
    async loginFn() {
      //ajax登录
      let { data } = await this.$request.login(this.user);
      //处理结果
      if (data.code == 1) {
        //登录成功

        //将同样的数据,保存到sessionStorage中
        sessionStorage.setItem("user", JSON.stringify(data.other.user));

   //将数据保存vue
        this.setData({ key: "user", value: data.other.user });

        //保存token
        sessionStorage.setItem("token", data.other.token);
     
        //跳转到首页
        // this.$router.push('/');     //会出错,index快报的功能出现app.$request.findNews();未定义
        
// ----------------------------------------------
// 如果有跳转记录,跳转到指定页面,否则跳转到首页
 let returnURL=sessionStorage.getItem('returnURL')
 if(returnURL){
   //需要跳转页面
   location.href=returnURL;
 }else{
     //跳转首页--重定向到首页
        location.href='/'
 }

//----------------------------------------------

       /*  //默认跳转首页
        location.href='/' */
      } else {
        //失败
        //保存错误信息
        this.errorMsg = data.message;
        //刷新验证码
        this.changImg();
      }
    }
  }
};
</script>

<style>
</style>