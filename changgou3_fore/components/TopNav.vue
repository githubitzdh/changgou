<template>
  <!-- 顶部导航 start -->
  <div class="topnav">
    <div class="topnav_bd w990 bc">
      <div class="topnav_left"></div>
      <div class="topnav_right fr">
        <ul v-if="user">
          <li>
            您好，{{user.username}} 欢迎来到畅购！ [
            <a href="" @click="logout">退出</a> ]
          </li>

          <li class="line">|</li>
          <li>我的订单</li>
          <li class="line">|</li>
          <li>客户服务</li>
        </ul>

        
        <!-- 没有登录数据显示 -->
        <ul v-else>
          <li>
            [
            <a href="/Login">登录</a>] [
            <a href="/Register">免费注册</a>]
          </li>
          <li class="line">|</li>
          <li>客户服务</li>
        </ul>
      </div>
    </div>
  </div>
  <!-- 顶部导航 end -->
</template>

<script>
//导入mapState 映射函数
import { mapState, mapMutations } from "vuex";
export default {
  //在计算属性中确定使用变量
  computed: {
    ...mapState(["user"])
  },
  //页面加载成功
  mounted() {
    let userstr = sessionStorage.getItem("user");
    if (userstr) {
      //将字符串转换对象
      let a = JSON.parse(userstr);
      //再将对象添加vuex中
      this.setData({ key: "user", value: a });
    }
  },
  methods: {
    ...mapMutations(["setData"]),
    logout() {
      sessionStorage.removeItem("user");
      sessionStorage.removeItem("token");
      this.setData({ key: "user", value: null });
      this.$router.push("/login");
    }
  }
};
</script>

<style>
</style>
