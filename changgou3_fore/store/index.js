//state区域, 相当于定义变量
export const state = () => ({
    user: null,
    keyword:null
  })
  
  //数据修改区域,用于改变变量的值
  export const mutations = {
    setData( state , obj) {   //通用方法,可以修改任意变量
     state[obj.key]=obj.value
    }

//修改数据: this.$store.commit('setData',{'key':'user','value':'xxx'} )




//   //state区域, 相当于定义变量
// export const state = () => ({
//   user: null
// })

// //数据修改区域,用于改变变量的值

// export const mutations = {
//   setUser( state , value) {
//     state.user=value
//   }
// }

// //修改数据  this.$store.commit('setUser','xxx') 


  }