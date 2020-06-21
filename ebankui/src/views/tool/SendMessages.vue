<template>
  <div>
    <common-header></common-header>
    <div class="main">
      <mt-popup v-model="popupVisible" position="bottom" class="mint-popup-3">
        <div class="model-picker">
          <mt-picker :slots="modelOptions" @change="onValuesChange" @click="popupVisible=false"></mt-picker>
        </div>
        <!-- <mt-button  size="small" type="primary">生成链接</mt-button> -->
      </mt-popup>
      <!-- <mt-popup v-model="popupVisible" popup-transition="popup-fade" :modal="true">
        <mt-picker :slots="modelOptions" @change="onValuesChange"></mt-picker>
      </mt-popup> -->
      <mt-field label="短信内容" placeholder="请输入短信内容" type="textarea" rows="4" v-model="msg"></mt-field>
      <mt-field label="选择excel">
        <input type="file" id="excelInputFile" v-on:change="fileChange()" />
      </mt-field>
      <mt-field label="电话列名" placeholder="请输入电话号码所在的列名" v-model="columnName"></mt-field>
      <mt-cell>
        <mt-button @click="buildMsg" size="small" type="primary">生成链接</mt-button>
      </mt-cell>
      <mt-cell>
        <mt-button @click="getLink" size="small" type="primary">获取链接</mt-button>
      </mt-cell>
      <mt-cell>
        <mt-button @click="saveLink" size="small" :disabled="!ready" type="primary">保存链接</mt-button>
      </mt-cell>
      <mt-cell>
        <mt-button @click="sendMsg" size="small" type="primary" :disabled="!ready">发送短信</mt-button>
      </mt-cell>
      <mt-cell>
        <mt-button @click="popupVisible=true" size="small" type="primary">test</mt-button>
      </mt-cell>
      <a :href="telhref" ref="tela" style="visibility:hidden;"></a>
    </div>
  </div>
</template>
<script>
import XLSX from "xlsx";
import CommonHeader from "@/components/main/CommonHeader";
import { saveLink, getLink, getMessageModels } from "@/api/phone-message";
export default {
  name: "SendMessages",
  components: {
    "common-header": CommonHeader
  },
  data() {
    return {
      msg: "",
      columnName: "",
      filePath: "",
      telhref: "",
      ready: false,
      phones: "",
      popupVisible: false,
      selectMode: "",
      modelOptions: [
        {
          values: ["自定义"],
          className: "mint-popup-3",
          textAlign: "center",
          defaultIndex: 0
        }
      ],
      models: { 自定义: { id: 0, name: "自定义", content: "" } }
    };
  },
  beforeMount() {
    getMessageModels().then(res => {
      var data = res.body;
      console.log(data);
      //   this.models["自定义"] = { id: 0, name: "自定义", content: "" };
      //   this.modelOptions.push("自定义");
      //   for (var i = 0; i < data.length; i++) {
      //     this.modelOptions.push(data[i].name);
      //     this.models[data[i].name] = data[i];
      //   }
      //   console.log(this.modelOptions);
      //   console.log(this.models);
    });
  },
  methods: {
    fileChange() {
      this.filepath = document.getElementById("excelInputFile").value;
    },
    buildMsg() {
      this.ready = false;
      if (this.filepath == "" || this.msg == "" || this.columnName == "") {
        this.$toast({
          message: "数据不全",
          position: "top",
          duration: 2000
        });
        return;
      }
      var that = this;
      var loading = this.$loading({
        lock: true,
        text: "构建链接中",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });
      this.readWorkbookFromLocalFile(
        document.getElementById("excelInputFile").files[0],
        function(workbook) {
          var sheetNames = workbook.SheetNames; // 工作表名称集合
          var worksheet = workbook.Sheets[sheetNames[0]]; // 这里我们只读取第一张sheet
          var datajson = XLSX.utils.sheet_to_json(worksheet);
          var myList = new Array();
          for (var i = 0; i < datajson.length; i++) {
            myList.push(datajson[i][that.columnName]);
          }
          that.phones = myList.join(",");
          that.buildHref();
          loading.close();
          that.ready = true;
        }
      );
    },
    sendMsg() {
      this.$refs.tela.click();
    },
    buildHref() {
      this.telhref = "sms://" + this.phones + "?body=" + this.msg;
    },
    readWorkbookFromLocalFile(file, callback) {
      var reader = new FileReader();
      reader.onload = function(e) {
        var data = e.target.result;
        var workbook = XLSX.read(data, { type: "binary" });
        if (callback) callback(workbook);
      };
      reader.readAsBinaryString(file);
    },
    saveLink() {
      saveLink({ phones: this.phones, body: this.msg })
        .then(() => {
          this.$toast("保存成功");
        })
        .catch(() => {
          this.$toast("保存失败");
        });
    },
    getLink() {
      getLink()
        .then(res => {
          console.log(res);
          var data = res.body;
          this.phones = data.phones;
          this.msg = data.body;
          this.$toast("获取成功");
        })
        .catch(err => {
          console.log(err);
          this.$toast("获取失败");
        });
    },
    onValuesChange() {}
  }
};
</script>
<style scoped>
.main {
  margin-top: 40px;
}
.mint-popup-3 {
    width: 100%;
    height: 100%;
    background-color: #fff;
}
</style>