<template>
  <div class="mains">
    <div class="main-img">
      <div class="main-img-text">
        <div class="text-title">
          <h1>深度定制</h1>
          <p>附加多项参数创建更加满意的作品</p>
        </div>
        <img src="../../assets/D.png" class="D">
        <img src="../../assets/I.png" class="I">
        <img src="../../assets/Y.png" class="Y">
      </div>
    </div>
    <div class="title">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <h1>选择图片</h1>
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
                    <p>拖拽你喜爱 <span class="c-homepage__slogan-heart"></span>图片进入 或者<br>点击从本地资源选取图片</p>
                  </div>
                  <img v-else :src="img1" class="img-responsive big">
                </form>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <h1>选择样式</h1>
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
                    <p>拖拽你喜爱 <span class="c-homepage__slogan-heart"></span>图片进入 或者<br>点击从本地资源选取图片</p>
                  </div>
                  <img v-else :src="img2" class="img-responsive big">
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="choice">
      <div class="container bors">
        <div class="row">
          <div class="col-md-6">
            <el-row class="demo-autocomplete">
              <el-col :span="12">
                <div class="sub-title">选择内容图片分辨率</div>
                <el-autocomplete
                  class="inline-input"
                  v-model="content_size"
                  :fetch-suggestions="querySearch"
                  placeholder="请输入内容"
                  oninput = "value=value.replace(/[^\d]/g,'')"
                ></el-autocomplete>
              </el-col>
              <el-col :span="12">
                <div class="sub-title">选择样式图片分辨率</div>
                <el-autocomplete
                  class="inline-input"
                  v-model="style_size"
                  :fetch-suggestions="querySearch"
                  placeholder="请输入内容"
                  oninput = "value=value.replace(/[^\d]/g,'')"
                ></el-autocomplete>
              </el-col>
            </el-row>
          </div>
          <div class="col-md-6">
            <el-row>
              <el-col :span="12">
                <div class="sub-title">是否保留内容图片颜色</div>
                <el-select v-model="color" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="12">
                <div class="sub-title">选择样式迁移程度(0-1)</div>
                <el-input-number v-model="num" :step="0.1"></el-input-number>
              </el-col>
            </el-row>
          </div>

        </div>
        <div class="row mail_container">
          <div class="col-lg-4 mail_title">e-mail</div>
          <div class="col-lg-7">
             <el-input v-model="e_mail" type="email"  placeholder="请输入邮箱"></el-input>
          </div>
        </div>
        <div>
          <button class="btn btn-primary submit_mail" @click="submits">提交</button>
        </div>
      </div>
    </div>

    <div class="mutil">
      <div class="container">
        <div class="row" style="margin-bottom:10vh ">
          <div class="col-lg-3"></div>
          <div class="col-lg-6" style="font-size: 1.9rem;font-weight: 600">AMAZING！一张样式图片不够？多张融合样式满足你的创造力！</div>
          <div class="col-lg-3"></div>
        </div>
        <div class="row">
          <div class="col-lg-9">
            <img src="../../assets/example_content.jpg" class=" example_content">
            <img src="../../assets/example_style2.jpg" class="example_style2">
            <img src="../../assets/example_style1.jpg" class="example_style1">
            <img src="../../assets/jia.png" class="example_content_jia">
            <img src="../../assets/example_result.jpg" class="example_result">
          </div>
          <div class="col-lg-3 muilt_title">
            <h1>定制多重样式风格</h1>
            <p>选取多张样式图片，组合来获得理想风格图片</p>
            <div class="btn  click_try">
             <el-button  type="danger" @click="dialogTableVisible = true">马上试一试</el-button>
            </div>
            <el-dialog  :visible.sync="dialogTableVisible">
              <Mulit></Mulit>
            </el-dialog>
          </div>
        </div>
      </div>
    </div>

    <el-dialog
      title="上传成功"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>上传成功，请注意查收邮箱</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
  import Mulit from './muilt'
    export default {
        name: "Deep_photo",
        data(){
            return{
                img1:'',
                img2:'',
                file1:null,
                file2:null,

                restaurants: [],
                content_size: '',
                style_size: '',
                color:'',
                options: [ {
                    value: 'true',
                    label: '保留'
                }, {
                    value: 'false',
                    label: '不保留'
                }],
                num:0,
                e_mail:'',

                dialogTableVisible: false,
                dialogVisible: false
            }
        },
        components:{
            Mulit
        },
        mounted(){
            this.restaurants = this.loadAll();
        },
        methods:{
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
            clickImgOne(){
                this.$refs.filedisplay.click();
            },
            getImgOne(event){
                this.file1 = event.target.files[0]; //获取input的图片file值
                this.img1=this.getImgURL(this.file1)
            },
            clickImgTwo(){
                this.$refs.filedisplayt.click()
            },
            getImgTwo(event){
                this.file2=event.target.files[0]
                this.img2=this.getImgURL(this.file2)
            },

            submits(){

                if(this.content_size=='低品质'||this.content_size<128) {
                    this.content_size = '64'
                }
                else if(this.content_size=='标准品质'||(this.content_size>=128&&this.content_size<512)){
                    this.content_size='256'
                }else{
                    this.content_size='512'
                }

                if(this.style_size=='低品质'||this.style_size<128) {
                    this.style_size = '64'
                }
                else if(this.style_size=='标准品质'||(this.style_size>=128&&this.style_size<512)){
                    this.style_size='256'
                }else{
                    this.style_size='512'
                }



                if(this.file1==null||this.file2==null) {
                    alert("请选择图片与样式")
                    return
                }

                if(this.color==''){
                    alert("请选择是否使用样式图片颜色")
                    return;
                }
                 if(this.num<=0||this.num>1){
                     alert("样式化程度仅能设置（0-1）")
                     return;
                 }
                  var re=/^\w+@[a-z0-9]+\.[a-z]+$/i
                  if (!re.test(this.e_mail)){
                      alert("请输入正确邮箱格式")
                      return;
                  }
                this.dialogVisible=true
                var formfile=new FormData()
                formfile.append("image",this.file1)
                formfile.append("style",this.file2)
                console.log("-------------------------------")
                console.log(this.content_size+'    '+this.style_size)
                var obj={
                    content_size:this.content_size,
                    style_size:this.style_size,
                    color:this.color,
                    count:this.num,
                    email:this.e_mail
                }
                  formfile.append("obj",JSON.stringify(obj))
                this.axios({
                    method:'post',
                    url:'/api/changeAll',
                    data: formfile,
                }).then((res)=>{
                    console.log(res)
                    // if(res.data.state=='success')
                    //     alert("计算完成,注意查收邮箱");
                    //相应后上传样式
                })
            },



            querySearch(queryString, cb) {
                var restaurants = this.restaurants;
                var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },
            createFilter(queryString) {
                return (restaurant) => {
                    return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
                };
            },
            loadAll() {
                return [
                    { "value": "低品质", "address": "64" },
                    { "value": "标准品质", "address": "256" },
                    { "value": "高品质", "address": "512" }
                ];
            },

        }
    }
</script>

<style scoped>
  .mains{
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }
  .muilt_title h1{
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-weight: 600;
    color: #a0a0a0;
    word-break:keep-all;
    white-space:nowrap;
  }
  .muilt_title p{
    text-align: center;
    margin-top: 60px;
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }

  .mutil{
    margin-top: 10vh;
    margin-bottom: 20vh;
  }
  .example_content{
    width: 22%;
    height: 18%;
    transform: rotate(-40deg);
    border-radius: 10px;
    box-shadow: 0 0 1px #03f0f4;
    margin-left: -20%;
  }
  .example_style1{
    width: 10%;
    height: 8%;
    margin-left: -2%;
    transform: rotate(-30deg);
  }
  .example_style2{
    width: 14%;
    height: 10%;
    margin-left: -10%;
    margin-bottom: -14%;
    transform: rotate(20deg);

  }
  .example_result{
    width: 33%;
    height: 16%;
    transform: rotate(10deg);
    border-radius: 10px;
    box-shadow: 0 0 1px #e3e3e6;
  }
  .example_content_jia{
    margin-left: 10px;
  }
  .main-img{
    height: 400px;
    background: url(../../assets/back2.jpg) no-repeat center center / cover;
    color: #fff;
    text-align: center;
    text-shadow: 0px 0px 0.5em #ffffff;
    font-size: 150%;
    padding: 0;
    margin-top: -10px;
  }
  .main-img-text{
    padding-top: 117px;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.24);
    display: flex;
  }
  .text-title{
    margin-left: 30%;
  }
  .D{
    width: 10%;
    height: 46%;
    margin-top: 6px;
    margin-left: 2%;
    transform: rotate(10deg);
  }
  .I{
    width: 10%;
    height: 46%;
    margin-left: 5px;
    margin-top: 26px;
    transform: rotate(-40deg);
  }
  .Y{
    width: 10%;
    height: 48%;
    margin-left: 2px;
    transform: rotate(-5deg);
  }
  .main-img-text h1{
    font-weight: 300;
    text-transform: uppercase;
  }
  .main-img-text p{
    margin-bottom: 22px;
    font-size: 18px;
    font-weight: 300;
    line-height: 1.4;
  }
  .dropzone {
    min-height: 150px;
    border: 2px dashed #9f9f9f;
    border-radius: 5px;
    background: white;
    padding: 20px 20px;
  }
  .dz-message {
    /*text-align: center;*/
    /*margin: 2em 0;*/
    font-family: Georgia,"Times New Roman",Times,serif;
    /*font-size: 20px;*/
    /*font-style: italic;*/
    /*font-family: "Centrale Sans Rounded Bold",Arial,sans-serif;*/
    font-size: 26px;
    color: #8ea8ba;
  }
  .c-homepage__slogan-heart {
    display: inline-block;
    background: url(../../assets/icon-heart.svg) no-repeat center;
    background-size: 100%;
    width: 24px;
    height: 20px;
  }
  .title h1{
    font-weight: 300;
    text-transform: uppercase;
    font-family: Microsoft YaHei;
  }
  .big{
    max-width: 200px;
    max-height: 200px;
  }
  .choice{
    padding-top: 30px;
  }
  .choice .bors{
    border:1px solid #e3e3e6;
    box-shadow: 1px 1px 0px #888888;
  }
  .sub-title{
    font-family: "Microsoft YaHei", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-weight: 600;
    margin: 20px;
  }
  .mail_container{
    margin-top: 50px;
  }
  .mail_title{
    text-align: center;
    font-family: Georgia,"Times New Roman",Times,serif;
    font-size: 30px;
    font-style: italic;
  }
  .submit_mail{
    margin-top: 20px;
    margin-left: 70%;
    width: 100px;
    margin-bottom: 20px;
  }
  .click_try{
    margin-top: 30px;
  }



</style>
