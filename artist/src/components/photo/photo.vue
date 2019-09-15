<template>
  <div>
    <div class="title">
      <h4 class="text-center">实时风格迁移</h4>
      <div></div>
    </div>
    <div class="col-md-6">
      <div class="font1">内容图片</div>
      <ul class="nav nav-tabs">
        <li class="active">
          <a data-toggle="tab" href="#" class="tab" aria-expanded="true">上传内容</a>
        </li>
      </ul>
      <div class="tab-content">

        <div class="tab-pane active">
          <form method="post" class="dropzone  dz-clickable" @click="clickImgOne">
            <input type="file" accept="image/*" style="display: none" ref="filedisplay" multiple="multiple" @change="getImgOne($event)">
            <div v-if="img1.length<2" class="dz-message">
              <p>拖拽图片进入 或者<br>点击从本地资源选取图片</p>
            </div>
            <img v-else :src="img1" class="img-responsive big">
          </form>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="font1">风格图片</div>
      <ul class="nav nav-tabs">
        <li class="active">
          <a data-toggle="tab" href="#" class="tab" aria-expanded="true">上传风格</a>
        </li>
      </ul>
      <div class="tab-content">
        <div class="tab-pane active">
          <form method="post" class="dropzone  dz-clickable" @click="clickImgTwo">
            <input type="file" accept="image/*" style="display: none" ref="filedisplayt" multiple="multiple" @change="getImgTwo($event)">
            <div v-if="img2.length<2" class="dz-message">
              <p>拖拽图片进入 或者<br>点击从本地资源选取图片</p>
            </div>
            <img v-else :src="img2" class="img-responsive big">
          </form>
        </div>
      </div>
    </div>
    <div class="up">
      <button class="btn btn-info" @click="submitImg">上传</button>
    </div>

    <div class="title">
      <h4 class="text-center">图片生成</h4>
      <div></div>
    </div>

    <div>
      <div class="result">
        <img class="img-responsive tip" src="../../assets/ww.png">

        <div class="container">
          <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-6 wait_little">（新生成的图片将替换下方图片，请稍等...）</div>
            <div class="col-lg-3"></div>
          </div>
          <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-6  bor">
              <div class="spinner" v-if="imgUrl==''">
                <div class="cube1"></div>
                <div class="cube2"></div>
              </div>
              <img :src="imgUrl" v-else>
            </div>
            <div class="col-lg-3"></div>
          </div>
        </div>
        <p class="men">
          我们在此提供实时预览的体验操作，耗费时间主要取决于您上传图片的大小<br>
          若需要更多精彩效果，可以使用深度定制功能生成您的照片
        </p>
      </div>
    </div>


    <el-dialog
      title="上传成功"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>上传成功，大约30秒后会在下方展示区展示</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
    export default {
        name: "photo",
        data(){
            return{
              file1:null,
              file2:null,
                imgUrl:'',
                img1:'',
                img2:'',
                dialogVisible: false
            }
        },
        mounted(){
          this.file1=null
          this.file2=null
          this.imgUrl=''
        },
        methods:{
            clickImgOne(){
              this.$refs.filedisplay.click();
            },
            getImgOne(event){
                this.file1 = event.target.files[0]; //获取input的图片file值
                this.img1=this.getImgURL(this.file1)
            },
            getImgURL(file) {
                var url = null;
                if(window.createObjectURL != undefined) { // basic
                    url = window.createObjectURL(file);
                } else if(window.URL != undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file);
                } else if(window.webkitURL != undefined) { // web_kit or chrome
                    url = window.webkitURL.createObjectURL(file);
                }
                return url;
            },
            clickImgTwo(){
                this.$refs.filedisplayt.click()
            },
            getImgTwo(event){
                this.file2=event.target.files[0]
                this.img2=this.getImgURL(this.file2)
            },
            submitImg(){
                if(this.file1==null||this.file2==null) {
                    alert("请选择图片与样式")
                    return
                }
                this.dialogVisible=true
                var formfile=new FormData()
                //上传
                formfile.append("image",this.file1)
                formfile.append("style",this.file2)
                this.axios({
                    method:'post',
                    url:'/api/img',
                    data:formfile
                }).then((res)=>{
                    console.log(res)
                    if (res.data.state=='success'){
                        this.changeImg(res)
                        console.log("上传成共")
                    }else
                        alert("上传失败")
                    //相应后上传样式
                })

            },
            changeImg(res){
                var that=this
                var img=res.data.image_name
                var sty=res.data.style_name
                this.axios.get('/api/changeImg?image_name='+img+'&style_name='+sty).then(function (resp) {
                    that.imgUrl='http://www.jeafoo.top/api/'+resp.data.urls
                    console.log(that.imgUrl)
                })
            },
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

<style scoped>
  .dropzone {
    min-height: 150px;
    border: 2px dashed #9f9f9f;
    border-radius: 5px;
    background: white;
    padding: 20px 20px;
  }
  .dropzone.dz-clickable {
    cursor: pointer;
  }
  .dropzone .dz-message {
    text-align: center;
    margin: 2em 0;
    font-family: Georgia,"Times New Roman",Times,serif;
    font-size: 20px;
    font-style: italic;

  }
  .title{
    margin-top: 10%;
  }
  .title h4 {
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 22px;
    font-weight: bold;
    letter-spacing: 4px;
    text-indent: 6px;
    color: #2f2e2e;
  }
  .title div {
    height: 3px;
    width: 40px;
    background-color: #f7931d;
    margin: 20px auto 35px auto;
  }
  .links-to-clients{
    margin-top: 5%;
  }
  .links-to-clients a {
    display: block;
    text-decoration: none;
    padding: 50px 0;
    box-shadow: 0 0 24px 0 rgba(185,185,185,0.25);
    text-align: center;
    margin-bottom: 20px;
  }
  .links-to-clients img {
    margin-bottom: 20px;
    vertical-align: middle;
    display: inline-block;
  }
  .links-to-clients h5 {
    color: #333333;
    font-weight: bold;
    font-size: 16px;
    margin-bottom: 5px;
  }
  .links-to-clients p {
    color: #828B8D;
    margin-bottom: 0;
  }
  .result{
    position: relative;
    padding: 40px 40px 40px 40px;
    box-shadow: 0 0 24px 0 rgba(185,185,185,0.25);
    margin: 25px 25px 25px 25px;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    border-radius: 10px;
  }
  .tip {
    position: absolute;
    top: -65px;
    left: 0;
    vertical-align: middle;
    width: 130px;
    height: 130px;
    border-radius: 100px;
    border: 2px dashed #e4d972;
  }
  .men {
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 20px;
    font-style: italic;
    color: #666666;
    padding: 0;
    border: none;
    margin-top: 5%;
  }
 .bor{
   padding: 2px;
   border: 1px solid #f1f1f1;;
 }

  .up button{

    margin-top: 30px;
  }
  .big{
    max-width: 200px;
    max-height: 200px;
  }

.wait_little{
  text-align: center;
  font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  margin-bottom: 6px;
}
  .font1 {
    color: transparent;
    -webkit-text-stroke: 2px #a2b7c2;
    letter-spacing: 0.04em;
    font-size: 40px;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }

  .spinner {
    margin-left: 46%;
    margin-top: 20%;
    margin-bottom: 20%;
    width: 40px;
    height: 40px;
    position: relative;
  }

  .cube1, .cube2 {
    background-color: #f8b85b;
    width: 90%;
    height: 90%;
    position: absolute;
    top: 0;
    left: 0;

    -webkit-animation: cubemove 1.8s infinite ease-in-out;
    animation: cubemove 1.8s infinite ease-in-out;
  }

  .cube2 {
    -webkit-animation-delay: -0.9s;
    animation-delay: -0.9s;
  }

  @-webkit-keyframes cubemove {
    25% { -webkit-transform: translateX(42px) rotate(-90deg) scale(0.5) }
    50% { -webkit-transform: translateX(42px) translateY(42px) rotate(-180deg) }
    75% { -webkit-transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5) }
    100% { -webkit-transform: rotate(-360deg) }
  }

  @keyframes cubemove {
    25% {
      transform: translateX(42px) rotate(-90deg) scale(0.5);
      -webkit-transform: translateX(42px) rotate(-90deg) scale(0.5);
    } 50% {
        transform: translateX(42px) translateY(42px) rotate(-179deg);
        -webkit-transform: translateX(42px) translateY(42px) rotate(-179deg);
      } 50.1% {
          transform: translateX(42px) translateY(42px) rotate(-180deg);
          -webkit-transform: translateX(42px) translateY(42px) rotate(-180deg);
        } 75% {
            transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5);
            -webkit-transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5);
          } 100% {
              transform: rotate(-360deg);
              -webkit-transform: rotate(-360deg);
            }
  }



</style>
