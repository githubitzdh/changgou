const request = {
    //快报
   
    findNews : () => {
      //return axios.get('/cgwebservice/news?pageNum=1&pageSize=5&sortWay=desc')
      return axios.get('/cgwebservice/news' , {
        params : {
          pageNum : 1,
          pageSize : 5 ,
          sortWay : 'desc'
        }
      })
    },
    //查询所有分类
    findCategorys:()=>{
      return axios.get("/cgwebservice/categorys")
    },
    //查询商品详情
    getGoodsInfo:(skuId)=>{
      return axios.get("/cgwebservice/sku/goods/" + skuId)
    }
  }
  
  var axios = null
  export default ({ $axios, redirect, process }, inject) => {
  
    //赋值
    axios = $axios
  
    //4) 将自定义函数交于nuxt
    // 使用方式1：在vue中，this.$request.xxx()
    // 使用方式2：在nuxt的asyncData中，content.app.$request.xxx()
    inject('request', request)
  }