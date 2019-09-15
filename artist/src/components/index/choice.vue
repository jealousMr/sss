<template>

<div class="main_container">
  <div class="content_title">
    <h1>样式选取 / <b>style show</b></h1>
    <p>
      或许您暂时没有找到喜欢的样式，为此我们提供以下样式供您选择
    </p>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-sm-6 to_margin" v-for="(item,index) in img_number">
        <div class="box">
          <div class="box-img">
            <img :src="getImgSrc(item)">
          </div>
          <div :class="'box-content box-content'+item">
            <h4 class="title">Light</h4>
            <div class="content_list_title clear">
              <div class="list_title_left fl">
                <p>选择您所喜欢的风格</p>
                <div class="enter_btn">
                  <el-button style="background-color: transparent;color: white;font-family: 'Microsoft YaHei'"  @click="open_dia(item)" round="">点击使用该风格</el-button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <button class="btn btn-info more_style pull-right" @click="next_page">点击获得更多样式</button>
    </div>
  </div>


  <el-dialog  title="DIY_Style" :visible.sync="dialogTableVisible">
    <div class="row diy_container">
      <div class="col-lg-3">
        <span class="em">E-mail</span>
      </div>
      <div class="col-lg-9 form-group">
        <input type="email" class="form-control" v-model="email">
      </div>
    </div>
    <div class="row diy_container">
      <div class="col-lg-6">
        <h1 style="text-align: center">选择你的照片</h1>
        <p class="btn btn-default" @click="sub_mit" style="color: #1ad383">
          提交<i>❤</i>
        </p>
      </div>
      <div class="col-lg-6">
          <div class="tab-pane active">
            <form method="post" class="dropzone  dz-clickable" @click="clickImgOne">
              <input type="file" accept="image/*" style="display: none" ref="filedisplayt" multiple="multiple" @change="getImgOne($event)">
              <div class="dz-message" v-if="img_url.length<2">
                <p>拖拽图片进入 或者<br>点击从本地资源选取图片</p>
              </div>
              <img v-else :src="img_url" class="big">
            </form>
          </div>
      </div>
    </div>
    <hr>
  </el-dialog>
</div>

</template>

<script>
    export default {
        name: "choice",
        data(){
            return{
                dialogTableVisible: false,
                email:'',
                img_url:'',
                file:null,
                img_id:0,
                img_number:[]
            }
        },
        mounted(){
            this.img_number=[]
            for(var i=1;i<=9;i++)
                this.img_number.push(i)
        },
        methods:{
            getImgSrc(index){
                switch (index) {
                    case 1:return require('../../assets/img1.jpg')
                    case 2:return require('../../assets/img2.jpg')
                    case 3:return require('../../assets/img3.jpg')
                    case 4:return require('../../assets/img4.jpg')
                    case 5:return require('../../assets/img5.jpg')
                    case 6:return require('../../assets/img6.jpg')
                    case 7:return require('../../assets/img7.jpg')
                    case 8:return require('../../assets/img8.jpg')
                    case 9:return require('../../assets/img9.jpg')
                    case 10:return require('../../assets/img10.jpg')
                    case 11:return require('../../assets/img11.jpg')
                    case 12:return require('../../assets/img12.jpg')
                    case 13:return require('../../assets/img13.jpg')
                    case 14:return require('../../assets/img14.jpg')
                    case 15:return require('../../assets/img15.jpg')
                    case 16:return require('../../assets/img16.jpg')
                    case 17:return require('../../assets/img17.jpg')
                    case 18:return require('../../assets/img18.jpg')
                    case 19:return require('../../assets/img19.jpg')
                    case 20:return require('../../assets/img20.jpg')
                    case 21:return require('../../assets/img21.jpg')
                    case 22:return require('../../assets/img22.jpg')
                    case 23:return require('../../assets/img23.jpg')
                    case 24:return require('../../assets/img24.jpg')
                    case 25:return require('../../assets/img25.jpg')
                    case 26:return require('../../assets/img26.jpg')
                    case 27:return require('../../assets/img27.jpg')
                }
            },
            next_page(){
              var last=this.img_number[this.img_number.length-1]
                this.img_number=[]
                if(last==9)
                    for(var i=10;i<=18;i++)
                        this.img_number.push(i)
                else if(last==18)
                    for(var i=19;i<=27;i++)
                        this.img_number.push(i)
                else if(last==27)
                    for(var i=1;i<=9;i++)
                        this.img_number.push(i)
            },



            clickImgOne(){
                this.$refs.filedisplayt.click();
            },
            getImgOne(event){
                this.file = event.target.files[0]; //获取input的图片file值
                this.img_url=this.getImgURL(this.file)
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

            open_dia(who){
                this.dialogTableVisible = true
                this.img_id=who
            },

            sub_mit(){
                var re=/^\w+@[a-z0-9]+\.[a-z]+$/i
                if (!re.test(this.email)){
                    alert("请输入正确邮箱格式")
                    return;
                }
                if(this.file==null){
                    alert("请选择你的照片")
                    return;
                }
                var obj={
                    email:this.email,
                    img_id:this.img_id
                }
                var formfile=new FormData()
                formfile.append("image",this.file)
                formfile.append("obj",JSON.stringify(obj))
                alert("上传成功,注意查收邮箱")
                this.axios({
                    method:'post',
                    url:'/api/choice_style',
                    data: formfile,
                }).then((res)=>{
                    console.log(res)
                    // if(res.data.state=='success')
                    //     alert("上传成功,注意查收邮箱")
                    //相应后上传样式
                })
            }

        }
    }
</script>

<style scoped>
  .dropzone {
    min-height: 60%;
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
  .diy_container h1{
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 30px;
    color: #a0a0a0;
    line-height: 1.5;
    text-align: left;
  }
  .diy_container p{
    font-family: "Roboto", "Helvetica Neue", Helvetica, Arial, sans-serif;
    margin-top: 25%;
    margin-right: 16px;
    color: #ffc55a;
  }
  .diy_container .em{
    text-align: center;
    font-family: Georgia,"Times New Roman",Times,serif;
    font-size: 30px;
    font-style: italic;
  }
  .diy_container p i{
    color: red;
    font-size: 18px;
  }
  .more_style{
    margin-top: 5%;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .suit_dia{
    width: 60%;
    height: 60%;
    border:1px solid #ffeeac;
    border-top-left-radius: 30px;
  }
.main_container{
  padding: 30px 0px;
  background-color: #f8f8f8;
  margin-top: 10px;
}
  .content_title{
    text-align: center;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
.content_title h1 {
  font-size: 38px;
  text-transform: uppercase;
  margin: 5px;
}
.content_title b{
  text-align: center;
  margin: 2em 0;
  font-family: Georgia,"Times New Roman",Times,serif;
  font-size: 20px;
  font-style: italic;
}
.content_title p {
  line-height: 22px;
  font-size: 14px;
  margin-top: 16px;
}


.box{
  position: relative;
  perspective: 1000px;
}
.box .box-img{
  transform: rotateY(0);
  transition: all 0.50s ease-in-out 0s;
}
.box:hover .box-img{
  transform: rotateY(-90deg);
}
.box .box-img img{
  width: 360px;
  height: 360px;
  border-radius: 10px;
  box-shadow: 0 0 2px #7f7f7f
}

  .box .box-content{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
  }
.box .box-content1{
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  padding: 60px 20px;
  text-align: center;
  transform: rotateY(90deg);
  transition: all 0.50s ease-in-out 0s;
  opacity: 0.8;
  background-image: url("../../assets/img1.jpg");
}
  .box .box-content2{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img2.jpg");
  }
  .box .box-content3{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img3.jpg");
  }
  .box .box-content4{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img4.jpg");
  }
  .box .box-content5{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img5.jpg");
  }
  .box .box-content6{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img6.jpg");
  }
  .box .box-content7{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img7.jpg");
  }
  .box .box-content8{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img8.jpg");
  }
  .box .box-content9{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img9.jpg");
  }
  .box .box-content10{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img10.jpg");
  }
  .box .box-content11{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img11.jpg");
  }
  .box .box-content12{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img12.jpg");
  }
  .box .box-content13{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img13.jpg");
  }
  .box .box-content14{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img14.jpg");
  }
  .box .box-content15{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img15.jpg");
  }
  .box .box-content16{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img16.jpg");
  }
  .box .box-content17{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img17.jpg");
  }
  .box .box-content18{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img18.jpg");
  }
  .box .box-content19{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img19.jpg");
  }
  .box .box-content20{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img20.jpg");
  }
  .box .box-content21{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img21.jpg");
  }
  .box .box-content22{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img22.jpg");
  }
  .box .box-content23{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img23.jpg");
  }
  .box .box-content24{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img24.jpg");
  }
  .box .box-content25{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img25.jpg");
  }
  .box .box-content26{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img26.jpg");
  }
  .box .box-content27{
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    padding: 60px 20px;
    text-align: center;
    transform: rotateY(90deg);
    transition: all 0.50s ease-in-out 0s;
    opacity: 0.8;
    background-image: url("../../assets/img27.jpg");
  }

.box:hover .box-content{
  transform: rotateY(0);
}
.box .title{
  font-size: 20px;
  color: #fff;
  text-transform: uppercase;
}
.box .description{
  font-size: 14px;
  line-height: 24px;
  color: #fff;
}
.box .title:after,
.box .description:after{
  content: "";
  width: 80%;
  display: block;
  border-bottom: 1px solid #fff;
  margin: 15px auto;
}

@media only screen and (max-width: 990px) {
  .box{  margin-bottom:20px; }
}
@media only screen and (max-width: 479px) {
  .box .box-content{ padding: 20px; }
}

  .enter_btn{
    margin-top: 30%;
  }
  .list_title_left p{
    font-family: Georgia,"Times New Roman",Times,serif;
    font-style: italic;
    font-weight: 600;
    color: white;
    margin-top: 12%;
  }
  .to_margin{
    margin-top: 33px;
  }
  .to_margin h4{
    text-align: center;
    font-family: Georgia,"Times New Roman",Times,serif;
    font-size: 30px;
    font-style: italic;
  }

.big{
  max-width: 200px;
  max-height: 200px;
}


</style>
