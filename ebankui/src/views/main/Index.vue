<template>
  <div>
    <mt-header fixed>
      <router-link to="/" slot="left">
        <img src="@/assets/tb.png" class="tbimg" />
      </router-link>
      <mt-button slot="right">
        <img src="@/assets/sz.png" class="czimag" />
      </mt-button>
    </mt-header>
    <mt-swipe :auto="4000">
      <mt-swipe-item>
        <img src="@/assets/swipe/Swipe1.jpg" class="swipeimag" />
      </mt-swipe-item>
      <mt-swipe-item>
        <img src="@/assets/swipe/Swipe2.jpg" class="swipeimag" />
      </mt-swipe-item>
      <mt-swipe-item>
        <img src="@/assets/swipe/Swipe3.jpg" class="swipeimag" />
      </mt-swipe-item>
    </mt-swipe>
    <div class="swipebg"></div>
    <div class="notice">
      <mt-cell title="公告" to="//github.com" is-link value="暂无通知">
      <img slot="icon" src="@/assets/home/notice.png" width="24" height="24">
      </mt-cell>
    </div>
    <div class="menu">
      <menu-item :name="name" :imageSrc="imageSrc" :path="path"></menu-item>
      <menu-item :name="name" :imageSrc="imageSrc" :path="path"></menu-item>
      <menu-item :name="name" :imageSrc="imageSrc" :path="path"></menu-item>
      <menu-item :name="name" :imageSrc="imageSrc" :path="path"></menu-item>
    </div>
  </div>
</template>
<script>
import MenuItem from "@/components/main/MenuItem";
import messagetb from "@/assets/home/message.png";
export default {
  name: "HomeIndex",
  data() {
    return {
      imageSrc: messagetb,
      name: "群发短信",
      path:'/Tool/SendMessages'
    };
  },
  components: {
    "menu-item": MenuItem
  }
};
</script>
<style scoped>
.mint-header {
  background-color: white;
}
.tbimg {
  height: 40px;
}
.czimag {
  height: 40px;
  width: 40px;
}
.swipeimag {
  width: 100%;
  overflow: visible;
  height: 192px;
}
.mint-swipe {
  height: 192px;
  top: 50px;
  margin: 0 10px;
  border-radius: 5px;
}
.menu {
  padding: 5px;
  margin: 5px 10px;
  background-color: white;
  border-radius: 5px;
  min-height: 80px; /*高度最小值设置为：100px*/
  height: auto !important; /*兼容FF,IE7也支持 !important标签*/
  height: 80px; /*兼容ie6*/
}
.swipebg {
  height: 50px;
  width: 100%;
  position: absolute;
  top: 40px;
  background-color: white;
  z-index: -1;
  border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;
}
.notice{
    margin: auto 10px;
    margin-top: 55px;
    border-radius: 5px;
    overflow: hidden;
}
</style>