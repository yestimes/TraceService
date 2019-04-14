# 网站API接口文档

## 用户
url: /user
### 登录
参数

    method： userLogin
    username： 用户名
    password: 密码
    
返回结果

    状态码 int status;
        200 成功
        40X 失败
        50X  服务器错误
        
    描述信息 String info;
        关于错误码的描述
        
    重定向地址 String url;
        登录成功之后发生的跳转
    
    示例：
    {"status":404,"info":"用户不存在","url":""}
    {"status":200,"info":"欢迎您，John","url":"console.html"}

## 产品

url: /product

### 获取产品详细信息

#### 参数
    method: getProductDetail
    
    pid: 产品id 
    //参数为pid，本意就是避免外部直接可以查询
   
#### 返回结果

    {
        产品id    int pid;
        产品名    String prodname;
        标题      String title;
        图片      String pict;
        相关描述   String descr;
        详情      String detail;
    }
         
####  相似接口
        method: toProductDetail
        调用后返回重定向，由重定向页面访问getProductDetail获取数据，刷新页面

### 获取产品列表
        method: getProductList
#### 返回结果
      {
        status,
        info,
        [
            { 
                产品id int pid;
                产品名 String prodname;
                产品缩略图路径 String prodpict_path
            },
            {
                ...
            }
        ]
      }

## 费用及API信息

url: /getCheckoutInfo    
    
    无参数，但必须登录。或者调用此接口前调用/user?method=userLogin

### 返回结果

    {
        status,
        info,
        [
            {
                 一级缩略图 String prodpict_path;
                 产品名 String prodname;
                 单价 float price;
                 计费起步次数 int batch;
                 实际使用  int count;
                 用户密钥 String appKey;
                 总价 float sum;
            },
            {
                ...
            }
            
        ]
    }
     
## 开通密钥

url: /purchase

    无参数，但是必须登录 并且 访问意向产品的详情页
    调用之前，需调用 /user?method=userLogin 
            和 /product?method=getProductDetail&pid=$(pid)  

### 返回结果

    {
        status,
        info,
         获取的API密钥 String key;
         跳转信息 String url;
    }
    
    
 ## 验证码安全配置下发
 
  url: /pictConf
 
 参数
 
    method: getInitConf
    
### 返回结果

    {
        status,
        into,
       时间戳 time;
       随机校验码 String number;
       拼图来源 imgSrc;
       小块X坐标 double x;
       小块Y坐标 double y;
    }
    
## 轨迹识别
url: /traceValidate

参数

    轨迹数量: dataLength  
    轨迹:     traceData
    API密钥: key
    
返回结果
 
    {
         int status;
         String info;
         数据可靠性 boolean flag;
         判断结果 boolean isMachine;

    }    


    
 