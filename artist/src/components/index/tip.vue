<template>
  <div class="mains">
    <div class="main_con">
      <el-carousel :interval="7000" arrow="always" :height="img_height">
        <el-carousel-item  v-for="item in 4" :key="item">
              <div v-if="item==1" class="main-img">
                <div class="main-img-text">
                  <h1>将您的照片重绘成为<b>艺术品</b></h1>
                  <p>使用您喜欢的<b>任何样式</b></p>
                  <router-link to="/photo" class="btn btn-default">简单尝试</router-link>
                  <router-link to="/deep_photo" class="btn btn-default">深度定制(DIY)</router-link>
                </div>
              </div>
              <div v-if="item==2" class="main-img2">
                 <div class="main-img-text">
                   <h1>将您的照片重绘成为<b>艺术品</b></h1>
                   <p>使用您喜欢的<b>任何样式</b></p>
                   <router-link to="/photo" class="btn btn-default">简单尝试</router-link>
                   <router-link to="/deep_photo" class="btn btn-default">深度定制(DIY)</router-link>
                 </div>
          </div>
              <div v-if="item==3" class="main-img3">
            <div class="main-img-text">
              <h1>将您的照片重绘成为<b>艺术品</b></h1>
              <p>使用您喜欢的<b>任何样式</b></p>
              <router-link to="/photo" class="btn btn-default">简单尝试</router-link>
              <router-link to="/deep_photo" class="btn btn-default">深度定制(DIY)</router-link>
            </div>
          </div>
              <div v-if="item==4" class="main-img4">
            <div class="main-img-text">
              <h1>将您的照片重绘成为<b>艺术品</b></h1>
              <p>使用您喜欢的<b>任何样式</b></p>
              <router-link to="/photo" class="btn btn-default">简单尝试</router-link>
              <router-link to="/deep_photo" class="btn btn-default">深度定制(DIY)</router-link>
            </div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="how-to-work">
      <div class="container">
        <h1>将任意照片重绘为您喜欢的风格</h1>
        <p>我们从<b>人工智能的深度学习</b>中获得启发，迁移一张图片风格重绘另一张图片内容</p>
        <ol class="list-unstyled row lis">
          <li class="col-md-4 col-sm-6">
            <div class="tt">
              <img src="../../assets/1.png" class="img-responsive">
              <h3>选择重绘照片</h3>
            </div>
            <p>内容图片</p>
            <img class="img-responsive tip_img" src="../../assets/tip_one.jpg">
          </li>
          <li class="col-md-4 col-sm-6">
            <div class="tt">
              <img src="../../assets/2.png" class="img-responsive">
              <h3>选择风格图片</h3>
            </div>
            <p>样式图片</p>
            <img class="img-responsive tip_img" src="../../assets/tip_two.jpg">
          </li>
          <li class="col-md-4 col-sm-6">
            <div class="tt">
              <img src="../../assets/3.png" class="img-responsive">
              <h3>完成提交</h3>
            </div>
            <p>在服务器运算后重绘图片</p>
            <img class="img-responsive tip_img" src="../../assets/tip_three.jpg">
          </li>
        </ol>
        <router-link to="/photo" class="btn btn-primary pull-left">试 一 试 </router-link>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "tip",
        data(){
            return{
                img_height:'420px'
            }
        }
    }
</script>

<style scoped>
  .mains{
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
.main_con{
  padding: 0;
  margin: 0;
  margin-top: -8px;
}

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

.main-img{
  height: 420px;
  background: url(../../assets/back1.jpg) no-repeat center center / cover;
  color: #fff;
  text-align: center;
  text-shadow: 0px 0px 0.5em #000;
  font-size: 150%;
  padding: 0;

}
  .main-img2{
    height: 420px;
    background: url(../../assets/back3.jpg) no-repeat center center / cover;
    color: #fff;
    text-align: center;
    text-shadow: 0px 0px 0.5em #000;
    font-size: 150%;
    padding: 0;
  }
  .main-img3{
    height: 420px;
    background: url(../../assets/back4.jpg) no-repeat center center / cover;
    color: #fff;
    text-align: center;
    text-shadow: 0px 0px 0.5em #000;
    font-size: 150%;
    padding: 0;
  }
  .main-img4{
    height: 420px;
    background: url(../../assets/back5.jpg) no-repeat center center / cover;
    color: #fff;
    text-align: center;
    text-shadow: 0px 0px 0.5em #000;
    font-size: 150%;
    padding: 0;
  }
.tip_img{
  max-height: 200px;
  width: 300px;
}
  .main-img-text{
    padding-top: 117px;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
  }
  .main-img-text a{
    color: white;background-color: transparent;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .main-img-text h1{
    font-weight: 300;
    text-transform: uppercase;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .main-img-text p{
    margin-bottom: 22px;
    font-size: 18px;
    font-weight: 300;
    line-height: 1.4;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .how-to-work h1{
    margin-top: 54px;
    font-weight: 300;
    text-transform: uppercase;
  }
  .how-to-work p{
    font-weight: 300;
    line-height: 1.4;
    font-size: 24px;
  }
  .lis{
    margin-bottom: 0;
  }
ol.lis li {
  counter-increment: item;
  margin-bottom: 36px;
  text-align: left;
}
ol.lis h3 {
  line-height: 32px;
  font-size: 18px;
  font-weight: 600;
  text-transform: none;
  color: #005b8c;
  margin-left: 40px;
}
  .tt{
    display: flex;
  }

</style>
