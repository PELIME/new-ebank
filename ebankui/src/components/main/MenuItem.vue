<template>
    <div class="menu-item" @click="HandMenuItem">
      <div class="img">
          <img :src="imageSrc"/>
      </div>
      <div class="name">
          {{name}}
      </div>
    </div>
</template>
<script>
export default {
 name:'HomeIndexMenuItem',
 props:{
     name:String,
     imageSrc:String,
     path:String
 },
 methods:{
     HandMenuItem(){
         this.$router.push(this.path)
     }
 }
}
</script>
<style scoped>
.menu-item{
    width: 25%;
    height: 60px;
    float: left;
    display: block;
}
.menu-item .img{
    margin: 5px auto;
    text-align: center;
}
.menu-item .img img{
    width: 40px;
    height: 40px;
    margin: auto;
    text-align: center;
}
.menu-item .name{
    margin: 5px auto;
    text-align: center;
    font: 0.5em sans-serif;
}
</style>