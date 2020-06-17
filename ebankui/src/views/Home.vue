<template>
  <div class="home">
    <router-view></router-view>
    <BottonMenu :selected="selected" @clickMenu="clickMenu"></BottonMenu>
  </div>
</template>
<script>
// @ is an alias to /src
import BottonMenu from '@/components/BottonMenu.vue'
export default {
  name: 'Home',
  data(){
    return{
      selected:'0'
    }
  },
  methods:{
    test(){
      this.active=1;
    },
    clickMenu(ac){
      this.selected=ac;
    }
  },
  components:{
    'BottonMenu':BottonMenu
  }
}
</script>
