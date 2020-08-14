<template>
  <div>
    <!-- 使用公共组件 -->
    <!-- 顶部导航start -->
    <TopNav></TopNav>
    <!-- 顶部导航end -->

    <div style="clear:both;"></div>

    <!-- 页面头部start -->
    <HeaderLogo></HeaderLogo>
    <!-- 页面头部end -->

    <div style="clear:both;"></div>
    <!-- 正文 -->

    <!-- 登录主体部分start -->
    <div class="login w990 bc mt10 regist">
      <div class="login_hd">
        <h2>用户注册</h2>
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
                  name="username"
                  v-model="user.username"
                  @blur="checkUsernameFn"
                />
                <p>3-20位字符，可由中文、字母、数字和下划线组成</p>
                <p :class="userMsg.username.code ==1?'success':'error'">
                  {{userMsg.username.message}}
                  </p>
              </li>
              <li>
                <label for>密码：</label>
                <input type="password" class="txt" name="password" v-model="user.password" />
                <p>6-20位字符，可使用字母、数字和符号的组合，不建议使用纯数字、纯字母、纯符号</p>
              </li>
              <li>
                <label for>确认密码：</label>
                <input type="password" class="txt" name="password" v-model="user.repassword" />
                <p>请再次输入密码</p>
              </li>
              <li>
                <label for>手机号码：</label>
                <input
                  type="text"
                  class="txt"
                  name="mobile"
                  v-model="user.mobile"
                  @blur="checkMobileFn"
                />
                <p>请输入手机号码</p>
                <!-- <p :class="userMsg.mobile.code==1?'success':'error'">{{userMsg.mobile.message}}</p> -->
                <p v-if="userMsg.mobile.code==1" class="success">{{userMsg.mobile.message}}</p>
                <p v-else class="error">{{userMsg.mobile.message}}</p>
              </li>
              <li class="checkcode">
                <label for>验证码：</label>
                <input type="text" name="checkcode" v-model="user.code" />
                <button @click.prevent="sendSmsFn" :disabled="btnDisabled">
                  发送验证码
                  <span v-if="btnDisabled">{{seconds}}秒</span>
                </button>
                <p class="error">{{userMsg.code}} </p>
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="checkbox" class="chb" checked="checked" /> 我已阅读并同意《用户注册协议》
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="submit" value @click.prevent="registerFn" class="login_btn" />
              </li>
            </ul>
          </form>
        </div>
        <div class="mobile fl">
          <h3>手机快速注册</h3>
          <p>
            中国大陆手机用户，编辑短信 “
            <strong>XX</strong>”发送到：
          </p>
          <p>
            <strong>1069099988</strong>
          </p>
        </div>
      </div>
    </div>
    <!-- 登录主体部分end -->


    <div style="clear:both;"></div>
    <!-- 底部版权start -->
    <Footer></Footer>
    <!-- 底部版权start -->
  </div>
</template>

<script>
// 1.1 导入公共组件
import TopNav from "../components/TopNav";
import HeaderLogo from "../components/HeaderLogo";
import Footer from "../components/Footer";
export default {
  head: {
    title: "用户注册",
    link: [{ rel: "stylesheet", href: "style/login.css" }],
    script: [ ]
  },
   //1.2 声明公共组件
  components: {
    TopNav,
    HeaderLogo,
    Footer
  },
  data() {
    return {
      user: {
        //与表单进行数据绑定
        username: "aa",
        mobile: "15012730793"
      },
      userMsg: {
        //错误信息
        username: {},
        mobile: {},
        code: "" //验证码提示信息
      },
      btnDisabled: false, //倒计时,用于控制显示按钮
      seconds: 5, //倒计时,用于记录秒数
      timer: null //当前定时器
    };
  },
  methods: {
    //校验用户名
    async checkUsernameFn() {
      //发送ajax进行检查
      let { data } = await this.$request.checkUsername(this.user);
      //处理结果(记录数据)
      this.userMsg.username = data;
    },

       //校验电话
    async checkMobileFn() {
      //发送ajax进行检查
      let { data } = await this.$request.checkMobile(this.user);
      //处理结果(记录数据)
      this.userMsg.mobile = data;
    },

    //发送短信
    async sendSmsFn() {
      //简答校验
      if (!this.user.username) {
        this.userMsg.username = {
          code: 0,
          message: "用户名不能为空"
        };
        return;
      }
      if (!this.user.mobile) {
        this.userMsg.mobile = {
          code: 0,
          message: "手机号不能为空"
        };
        return;
      }
      //倒计时start
      //1,按钮不可用
        this.btnDisabled=true;
      //2倒计时
      this.timer = setInterval(() => {
        //如果小于1,重置
        if (this.seconds <= 1) {
          //按钮可用
          this.btnDisabled = false;
          //时间5秒
          this.seconds = 5;
          //关闭定时器
          clearInterval(this.timer);
        } else {
          this.seconds--;
        }
      }, 1000);
      //倒计时end

      //继续发送
      let { data } = await this.$request.sendSms(this.user);
      if (data.code == 1) {
        //发送成功--给一个绿色小图标
        alert(data.message);
      } else {
        //错误信息
        this.userMsg.code = data.message;
      }
    },

    async registerFn() {
      //发送ajax
      let { data } = await this.$request.register(this.user);
      //处理
      if (data.code == 1) {
        alert(data.message)
        //注册成功,跳转登录页面
        this.$router.push("/login");
      } else {
        //提示信息
       this.usermsg.code=data.message
      }
    }
  }
};
</script>

<style>
</style>


