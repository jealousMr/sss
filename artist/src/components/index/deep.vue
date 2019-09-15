<template>
  <div class="browse-container">
      <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-10 cap">
          <div class="container">
            <div class="row">
              <div class="col-lg-2 "></div>
              <div class="col-lg-4 ">
                <img  src="../../assets/you.jpg" class="im1">
                <img  src="../../assets/ww.jpg" class="im2">
              </div>
              <div class="col-lg-6 ff ">
                <h1>深度定制<br >您自己的照片<br>将创造力发挥至极致</h1>
                <p >我们提供详细的参数以及多种实现方式</p>
                <router-link to="/deep_photo">
                  <button class="button-3d-1">立即尝试</button>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
    export default {
        name: "deep"
    }
</script>

<style scoped>
.browse-container{
  height: 55vh;
  overflow: hidden;
  margin-top: 40px;
}
  .cap{
    /*background-color: #ffa125;*/
    background-image: linear-gradient(to right , #283850, #e3dfdf);
    opacity: 0.9;
    height: 55vh;
    border-top-left-radius: 45vh;
  }
  .im1{
    width: 25vw;
    height: 25vh  ;
    z-index: -1;
    margin-top: 4vh;
    margin-left: 6vh;
    border-radius: .8rem;
  }
  .im2{
    width: 25vw;
    height: 26vh;
    margin-top: -10vh;
    margin-left: -10vh;
    border-radius: .8rem;
  }
  .cap h1{
    font-size: 2.8rem;
    font-weight: 800;
    line-height: 2rem;
    color: white;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .cap p{
    font-size: 2rem;
    line-height: 31px;
    color: #fff;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }


.code-btn-container {
  overflow: hidden;
  height: 5vh;
  width: 10vw;
  position: absolute;
  border-radius: 3px;
  letter-spacing: .5px;
  margin-left: 13vw;
  margin-top: 0;

}
.code-btn-container .code-text {
  height: 100%;
  width: 100%;
  background-color: #a2f497;
  border: 1px solid #bfbfbf;
  border-radius: 3px;
  position: absolute;
  left: 0;
  top: 0;
}
.code-text p {
  color: #444;
  text-align: right;
  padding: 10px 5px;
  font-weight: 600;
}



.code-btn span {
  display: inline-block;
  position: relative;
  z-index: 2;
  margin: 10px 8px 10px 12px;
  transition: margin .2s;
  font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-weight: 600;

}
  .ff{
    font-family: Georgia,"Times New Roman",Times,serif;
    font-style: italic;
    margin-top: 2%;
  }
  .ff h1{
    line-height: 5rem;
  }
  .ff p{
    margin-bottom: 6%;
  }

.button-3d-1{
  position: relative;
  background-image: linear-gradient(to right , #416d98, #a2b7c2);
  font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  border: none;
  color: white;
  padding: 15px 24px;
  font-size: 2rem;
  outline: none;
  box-shadow: -6px 6px 0 hsl(210, 39%, 34%);
}







</style>
