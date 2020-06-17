<template>
  <mt-tabbar v-model="selectedCom">
    <mt-tab-item id="0">
      <img slot="icon" v-if="selected==0" src="@/assets/HomeAc.png" />
      <img slot="icon" v-else src="@/assets/Home.png" />
      主页
    </mt-tab-item>
    <mt-tab-item id="1">
      <img slot="icon" v-if="selected==1" src="@/assets/ToolAc.png" />
      <img slot="icon" v-else src="@/assets/Tool.png" />
      工具
    </mt-tab-item>
    <mt-tab-item id="2">
      <img slot="icon" v-if="selected==2" src="@/assets/NewsAc.png" />
      <img slot="icon" v-else src="@/assets/News.png" />
      资讯
    </mt-tab-item>
    <mt-tab-item id="3">
        <img slot="icon" v-if="selected==3" src="@/assets/MeAc.png" />
      <img slot="icon" v-else src="@/assets/Me.png" />
      我的
    </mt-tab-item>
  </mt-tabbar>
</template>>
<script>
export default {
    name:'BottonMenu',
    props: {
    selected: String
    },
    methods:{
    },
    computed:{
        selectedCom:{
            get(){
                return this.selected;
            },
            set(s){
                this.$emit('clickMenu',s);
            }
        }
    }
}
</script>