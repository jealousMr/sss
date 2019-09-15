<template>
  <div>
    <Tip></Tip>
    <Choice></Choice>
    <Deep></Deep>
    <div class="about_us">
      <div class="row all">
        <div class="col-lg-4 one col-sm-12">
          <a name="about_us"></a>
          <h2>关于我们</h2>
          <p>DiligenceTeam</p>
          <p>联系邮箱：lcx19990918@163.com</p>
          <img style="width: 5vh;height: 5vh;margin-top: 4vh" src="../../assets/logo.png">
        </div>
        <div class="col-lg-8 two col-sm-12">
          <h2>我们用到的技术</h2>

            <p class="btn btn-default my-tools" style="background: transparent;color: white"><img style="width: 5vh;height: 5vh;margin-right: 1vw" src="../../assets/bootstrap.png">Bootstrap</p>
            <p class="btn btn-default my-tools" style="background: transparent;color: white"><img style="width: 5vh;height: 5vh;margin-right: 1vw" src="../../assets/jQuery.png">jQuery</p>
            <p class="btn btn-default my-tools" style="background: transparent;color: white"><img style="width: 5vh;height: 5vh;margin-right: 1vw" src="../../assets/vue.png">Vue</p>
            <p class="btn btn-default my-tools" style="background: transparent;color: white"><img style="width: 5vh;height: 5vh;margin-right: 1vw" src="../../assets/html5.jpg">HTML5</p>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
     import Tip from './tip'
     import Deep from './deep'
     import Choice from './choice'
    export default {
        name: "index",
        components:{
            Tip,
            Deep,
            Choice
        }
    }
</script>

<style scoped>
  .about_us{
    width: 86vw;
    height: 50vh;
    background-image: linear-gradient(to left , #202c41, #487dab);
    opacity: 0.9;
    border-top-right-radius: 50vh;
    color: white;
  }

  .about_us .one{
      margin-top: 3%;
  }
  .about_us .two{
    margin-top: 3%;
  }
  .one h2{
      font-family: Georgia,"Times New Roman",Times,serif;
  }
  .two h2{
      font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .one p{
    margin-top: 4vh;
  }
  .two h2 {
    margin-bottom: 8vh;
  }

  .two{
    margin-bottom: 10vh;
  }

  .my-tools{
    margin-top: 0!important;
    width: 10vw;
    font-size: 1.4rem;
  }

</style>
