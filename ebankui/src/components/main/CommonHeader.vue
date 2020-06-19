<template>
    <mt-header fixed title="群发短信">
      <mt-button icon="back" slot="left" @click="back">返回</mt-button>
    </mt-header>
</template>
<script>
export default {
    Name:'CommonHeader',
    methods:{
        back(){
            this.$router.back();
        }
    }
}
</script>
<style scoped>
</style>