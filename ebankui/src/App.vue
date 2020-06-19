<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
export default {
  name:'App'
}
</script>
<style scoped>
#app{
  background-color: #e4e0e0;
  width:100%;
  height:100%;
  overflow:hidden;
  margin:0;
  position: absolute;
  z-index: -999;
}
.mint-button-text{
  margin-bottom: 0 !important;
}
</style>>
