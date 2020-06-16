<template>
    <div>
        <div id="login-bg" class="container-fluid">

      <div class="bg-img"></div>
      <div class="bg-color"></div>
    </div>

    <!-- End Backgrounds -->

    <div class="container" id="login">
        <div class="row justify-content-center">
        <div class="col-lg-8">
          <div class="login">

            <h1>Login</h1>
            
            <!-- Loging form -->
                  <div>
                    <div class="form-group">
                      <input type="username" v-model="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="用户名 | 邮箱 | 电话号码">
                      
                    </div>
                    <div class="form-group">
                      <input type="password" v-model="password" class="form-control" id="exampleInputPassword1" placeholder="密码">
                    </div>

                      <div class="form-check">

                      <label class="switch">
                      <input type="checkbox" v-model="remember">
                      <span class="slider round"></span>
                    </label>
                      <label class="form-check-label">记住密码</label>
                      
                      <label class="forgot-password"><a href="#" @click="forget">忘记密码?</a></label>

                    </div>
                  
                    <br>
                    <button @click="login" class="btn btn-lg btn-block btn-success">登 录</button>
                  </div>
             <!-- End Loging form -->

          </div>注册请联系管理员  <a href="tel://13541210431" target="_blank" title="电话">电话</a>
        </div>
        </div>
    </div>
    </div>
</template>>
<style scoped>
@import '../../styles/styles.css'
</style>

<script>
export default {
    data(){
        return {
            username:'',
            password:'',
            remember:false,
            encode:false
        }
    },
     beforeMount:function(){
        var storage=window.localStorage;
        this.username=storage.getItem("username");
        this.password=storage.getItem("epwd");
        this.remember=storage.getItem("remember");
        if(this.password&&this.password!=""){
            this.encode=true;
        }
    },
    methods: {
        forget:function(){
            this.$message({
                type:'info',
                message:"请联系管理员找回密码"
            });
        },
        ememberMe:function(savePwd){
            var storage=window.localStorage;
            if(this.remember){
                storage.setItem("username",this.username);
                storage.setItem("epwd",savePwd);
                storage.setItem("remember",this.remember);
            }
            else{
                storage.removeItem("username");
                storage.removeItem("epwd");
                storage.removeItem("remember");
            }
        },
        login:function(){
            console.log('login')
        }
    }
}
</script>