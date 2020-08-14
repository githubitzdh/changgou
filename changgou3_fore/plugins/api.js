const request = {
    test : ()=> {
      return axios.get('/test')
    },
    //检查用户名
    checkUsername : ( user ) => {
      return axios.post('/cgwebservice/user/checkusername', user )
    },
    //检查电话号码
    checkMobile:(mobile)=>{
      return axios.post('/cgwebservice/user/checkmobile',mobile)
    },
     //发送短信
    sendSms:(user)=>{
      return axios.post('/cgwebservice/sms', user )
    },
    //注册用户
    register:(user)=>{
      return axios.post('/cgwebservice/user/register', user )
    },
    //登录
    login :(user)=>{
      return axios.post('/cgauthservice/auth/login', user )
    },
    //指定分类的品牌
    findBrand:(categoryId)=>{
      return axios.get('/cgwebservice/brands/category/' + categoryId)
    },
    //指定分类的规格
    findSpec:(categoryId)=>{
      return axios.get('/cgwebservice/specifications/category/' + categoryId)
    },
    //搜索
    search: ( params ) => {
      return axios.post('/cgsearchservice/sku/search', params)
    },
    //查询评论
    findComments:(spuid,pageSize,pageNum)=>{
      return axios.get(`/cgwebservice/comments/spu/${spuid}?pageNum=${pageSize}&pageSize=${pageNum}`)
    },
    //添加购物车
    addToCart:(params)=>{
      return axios.post("/gccartservice/carts", params )
    },
    //查询购物车
    selectCart:()=>{
      return axios.get("/gccartservice/carts" )
    },
    //更新购物车
    updateCart : ( cart ) => {
      return axios.put("/gccartservice/carts" ,cart )
    },
    //查询地址
    getAddress:()=>{
      return axios.get("/cgorderservice/address")
    },
    //添加联系人
    addNewAddress:(params)=>{
      return axios.post("/cgorderservice/address" , params )
    },
    //添加订单
    addOrder : ( params ) => {
      return axios.post("/cgorderservice/orders", params )
    },
    //获取微信支付路径
    pay : ( sn ) => {
      return axios.post("/cgpayservice/pay" ,{sn} )
    },
    //查询订单支付状态
    findPayStatus : ( sn ) => {
      return axios.get("/cgpayservice/pay/"+sn )
    }

  }
  
  var axios = null
  export default ({ $axios, redirect }, inject) => {

    //nuxt参合后 axios ,可以设置请求头
    //参考 https://axios.nuxtjs.org/helpers
     //获得token
    let token = sessionStorage.getItem('token')
    //如果token存在,设置请求头
    if( token ) {
      //nuxt整合axios提供的特有函数( Adds header: `Authorization: 123` to all requests)
      // this.$axios.setToken('123')
      $axios.setToken( token )
    }

      //处理响应异常
      //统一错误处理,处理403异常
  $axios.onError(error => {
    // token失效，服务器响应403
    if(error.response.status === 403) {
      console.error(error.response.data)
      redirect('/login')
      //如果有token,此时token失效,直接删除
      sessionStorage.removeItem('token')
    }
  })

    //赋值
    axios = $axios
  
    //4) 将自定义函数交于nuxt
    // 使用方式1：在vue中，this.$request.xxx()
    // 使用方式2：在nuxt的asyncData中，content.app.$request.xxx()
    inject('request', request)
  }