<template>
  <div id="app">
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <i class="navbar-link">
            <img class="logo" src="./assets/logo.png">
            <span>风 格 创 意</span>
          </i>
        </div>
        <div class="navbar-body pull-right">
          <ul class="nav navbar-nav">
            <li>
              <router-link to="/" class="links">首页</router-link>
            </li>
            <li>
              <router-link to="/photo" class="links">在线体验</router-link>
            </li>
            <li>
              <router-link to="/deep_photo" class="links">深度定制（DIY）</router-link>
            </li>
            <li>
              <a href="#about_us" class="links">关于我们</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <router-view/>
    <footer>
      <div class="footer_text">
        <p>This site uses cookies to ensure an amazing experience!</p>
        <div class="bb btn btn-info" @click="dialogVisible = true">Got it</div>
      </div>
      <el-dialog
        title="more"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span>更多功能敬请期待...</span>
        <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
      </el-dialog>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App',
    data(){
      return{
          dialogVisible: false
      }
    },
    methods: {
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        }
    }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
nav{
  font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
}
.navbar {
  min-height: 7vh;
  border: 1px solid transparent;
}
.navbar-fixed-top {
  top: 0;
  border-width: 0 0 1px;
}
.navbar-default {
  background-color: #f8f8f8;
  border-color: #e7e7e7;
}
.navbar-header {
  margin-right: 0;
  margin-left: 0;
}
.navbar-link{
  margin-left: -15px;
}
.navbar-link span{
  font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 20px;
  color: #0891ad;
  font-weight: bold;
  position: relative;
  display: inline-block;
  height: 7vh;
  line-height: 7vh;
  font-style: normal;
}
.logo{
  height: 5vh;
  width: 5vh;
  z-index: 10;
}
.navbar-body{
  max-height: 340px;
}
.navbar-nav li{
  float: left;

}
.links{
  display: inline-block;
  height: 7vh;
  line-height: 7vh;
  color: #777;
}
footer{
  font-family: Georgia,"Times New Roman",Times,serif;
  font-style: italic;
  color: white;
  background-color: #2f2e2e;
  opacity: 0.9;
  height: 50px;
  position: fixed;
  bottom: 0;
  width: 100%;
}
  .footer_text{
    margin-top: 1%;
    display: flex;
  }
  .footer_text p{
    margin-left: 5%;
    margin-top: 1px;
  }
  .footer_text .bb{
    margin-left: 54%;
    width: 100px;
  }

</style>
