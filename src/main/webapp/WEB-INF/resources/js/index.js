//主页相关的js代码
var webSocket;
var isCountdown = true; // 当定时器启动时，手机号码输入框的blur()不会使按钮解禁
var isPhoneChecked = false; // 手机号码是否校验通过，只有当这个为true,且isCountdown为true时，才解禁发送验证码按钮
var isImageCheckCode = false; // 图片验证码是否校验通过
var isCheckCode = false; // 手机验证码是否校验通过
var isPassword = false; // 密码是否通过验证
var isLoginPhone = false; // 登录 手机是否通过校验

var isChatModal = false;//判断聊天对话框是否打开
var index = {
    URL: { // 获取URL的对象
        checkPhoneURL: function (phone) { // 验证手机号码是否已经注册
            return 'user/checkPhone/' + phone;
        },
        sendCodeURL: function (phone) { // 发送注册的手机验证码
            return 'user/sendCode';
        },
        verifyImageCheckCodeURL: function (checkCode) { // 验证图片验证码是否正确
            return 'user/verifyImageCheckCode/' + checkCode;
        },
        verifyCheckCodeURL: function () { // 验证手机验证码是否正确
            return "user/verifyCheckCode";
        },
        registerURL: function () { // 注册
            return "user/register";
        },
        loginURL: function () { // 登录
            return "user/login";
        },
        userExitURL: function () { // 退出登录（注销）
            return "user/exit";
        },
        queryUserInfoURL: function () { // 获取用户基本信息
            return "user/query/userInfo";
        },
        editUserInfoURL: function () {//修改用户基本信息
            return "user/edit/userInfo";
        },
        queryUserDetailURL: function () {//查询用户详细信息
            return "user/query/userDetail";
        },
        editUserDetailURL: function () {//修改用户详细信息
            return "user/edit/userDetail";
        },
        headImageUploadURL: function () {//上传用户头像
            return "user/upload/headImage";
        },
        dictQueryByTypeURL: function () {//查询字典表
            return "dict/queryByType";
        },
        faceAgeTestURL: function () {//人脸年龄识别
            return "util/faceAgeTest";
        },
        aiChatURL: function () {//机器人聊天
            return "util/aiChat";
        },
        searchNameLikeURL: function () {//模糊查询用户 根据name
            return "user/search/name/like";
        },
        userIndexURL: function (userId) {//用户首页
            return "userIndex/" + userId;
        },
        loadMessageListURL: function () {//加载消息列表
            return "message/list";
        },
        messageDetailURL: function () {//查询消息详情
            return "message/detail";
        },
        messageChangeStateURL: function () {//修改消息状态
            return "message/change/state";
        },
        loadFriendListURl: function () {//加载好友列表
            return "friend/list";
        },
        loadChatRecordURL: function () {//加载聊天记录
            return "friend/chatRecord";
        },
        sendChatMessageURL: function () {//向好友发送聊天消息
            return "friend/chatMessage";
        },
        changeChatStateURL: function () {//修改 与某个用户间的消息状态为已读
            return "friend/changeChatState";
        }

    },
    // 页面初始化
    init: function () {
        index.message.initLoadMessageList();//消息列表初始化
        index.friend.initLoadFriendList();//好友列表初始化
        index.chat.init(); // 弹幕功能初始化
        index.user.autoUserInfoModal(); // 自动提示补全基本信息
        $._messengerDefaults = {// 初始化messenger插件显示位置
            extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom messenger-on-center'
        };
        index.user.queryUserByNameLike();//模糊搜索初始化

        //初始化fileinput
        var FileInput = function () {
            var oFile = new Object();
            //初始化fileinput控件（第一次初始化）
            oFile.Init = function (ctrlName, uploadUrl) {
                var control = $('#' + ctrlName);
                //初始化上传控件的样式
                control.fileinput({
                    language: 'zh', //设置语言
                    uploadUrl: uploadUrl, //上传的地址
                    allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
                    showUpload: true, //是否显示上传按钮
                    showCaption: false,//是否显示标题
                    browseClass: "btn btn-primary", //按钮样式
                    //dropZoneEnabled: false,//是否显示拖拽区域
                    maxFileSize: 102400,//单位为kb，如果为0表示不限制文件大小
                    maxFileCount: 1, //表示允许同时上传的最大文件个数
                    enctype: 'multipart/form-data',
                    validateInitialCount: true,
                    previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                    msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
                });
            }
            return oFile;
        };
        //导入文件上传完成之后的事件
        $("#imageFile").on("fileuploaded", function (event, data, previewId, index) {
            console.info(data);
            $('#headImageUploadModal').modal('hide');//关闭modal
            $.globalMessenger().post({
                message: '上传成功！',
                type: 'success',
                hideAfter: 3
            });
        });
        //导入文件上传完成之后的事件
        $("#faceImage").on("fileuploaded", function (event, data, previewId, index) {
            if (data.response) {
                $.globalMessenger().post({
                    message: '测试成功：年龄为' + data.response + '岁',
                    type: 'success',
                    hideAfter: 5
                });
            } else {
                $.globalMessenger().post({
                    message: '测试失败！',
                    type: 'error',
                    hideAfter: 3
                });
            }
        });
        $('#faceImage').on('fileuploaderror', function(event, data, msg) {
            $.globalMessenger().post({
                message: '测试失败！人脸无法识别！',
                type: 'error',
                hideAfter: 3
            });
        });
        var oFileInput = new FileInput();
        oFileInput.Init("imageFile", index.URL.headImageUploadURL());
        var fFileInput = new FileInput();
        fFileInput.Init("faceImage", index.URL.faceAgeTestURL());


        //初始化 详细信息modal
        $.post(index.URL.dictQueryByTypeURL(), {'type': 'height'}, function (strings) {
            if (strings) {
                var options = '';
                strings.forEach(function (string) {
                    options = options + '<option value="' + string + '">' + string + '</option>'
                });
                $('select[name=height]').html(options);

            }
        });
        $.post(index.URL.dictQueryByTypeURL(), {'type': 'weight'}, function (strings) {
            if (strings) {
                var options = '';
                strings.forEach(function (string) {
                    options = options + '<option value="' + string + '">' + string + '</option>'
                });
                $('select[name=weight]').html(options);

            }
        });
        $.post(index.URL.dictQueryByTypeURL(), {'type': 'color'}, function (strings) {
            if (strings) {
                var options = '';
                strings.forEach(function (string) {
                    options = options + '<option value="' + string + '">' + string + '</option>'
                });
                $('select[name=color]').html(options);

            }
        });

        //日期初始化
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: true,
            format: 'yyyy-mm-dd'
        });


        //打开聊天对话框的时候 设置变量为true
        $('#chatModal').on('show.bs.modal', function () {
            isChatModal = true;
        });

        //关闭聊天对话框
        $('#chatModal').on('hide.bs.modal', function () {
            isChatModal = false;
        });

        /*-------------------注册相关事件绑定-------------------*/
        // F:检查手机号码格式
        $('#registerPhone').blur(function () {
            var phone = $(this).val();
            index.checkPhone(phone); // 验证手机号码
        });
        // F: 发送验证码
        $('#sendCode').click(function () {
            if (!isCountdown || !isPhoneChecked || !isImageCheckCode) {
                $('#sendCodeMessage').hide().html('<label class="label label-danger">校验未通过，无法发送!</label>').show(500).delay(3000).hide(500);
                return;
            }
            $(this).attr({
                'disabled': 'disabled'
            });
            var registerPhone = $('#registerPhone').val(); // 获取手机号码
            var imageCheckCode = $('#imageCheckCode').val(); // 获取图片验证码
            // ajax，后台发送验证码
            $.post(index.URL.sendCodeURL(), {
                    'phone': registerPhone,
                    'imageCheckCode': imageCheckCode
                },
                function (result) {
                    if (result == true) {
                        // 进入60s倒计时
                        isCountdown = false;
                        var countdown = setInterval(countDown, 1000); // 给对象绑定定时器，每1S执行一次countDown()
                        var count = 60; // 秒数
                        // F:每秒运行一次的倒计时方法
                        function countDown() {
                            count--;
                            $('#sendCode').text('重新发送（' + count + 's)');
                            if (count == 0) {
                                clearInterval(countdown); // 清除定时器
                                isCountdown = true;
                                index.checkPhone($('#registerPhone').val()); // 验证手机号码
                                $('#sendCode').text('重新发送');
                            }
                        }
                    } else {
                        // 发送错误
                        $('#sendCodeMessage').hide().html('<label class="label label-danger">验证码发送错误!</label>').show(500).hide(500);
                        $(this).hide(500);
                        $(this).show(500);
                    }
                });
        });
        // F:验证图片验证码是否正确
        $('#imageCheckCode').blur(function () {
            $('#imageCheckCodeChecked').addClass('hidden');
            isImageCheckCode = false;
            var imageCheckCode = $(this).val(); // 图片验证码的值
            if (imageCheckCode != null && imageCheckCode != '' && imageCheckCode.length == 4) {
                $.post(index.URL.verifyImageCheckCodeURL(imageCheckCode), {},
                    function (result) {
                        if (result == true) {
                            $('#imageCheckCodeChecked').removeClass('hidden');
                            isImageCheckCode = true;
                            // 此时再验证手机号码是否校验通过，如果也通过，则发送验证码按钮生效
                            if (isPhoneChecked) {
                                $('#sendCode').removeAttr('disabled');
                            }
                        }
                    });
            }
        });
        // F:验证 手机验证码是否正确
        $('#checkCode').blur(function () {
            var checkCodeChecked = $('#checkCodeChecked');
            checkCodeChecked.addClass('hidden');
            isCheckCode = false;
            var checkCode = $(this).val();
            var phone = $('#registerPhone').val();
            if (checkCode.length != 4) {
                return;
            }
            $.post(index.URL.verifyCheckCodeURL(), {
                    'phone': phone,
                    'checkCode': checkCode
                },
                function (result) {
                    if (result == true) {
                        isCheckCode = true;
                        checkCodeChecked.removeClass('hidden');
                    }
                });
        });
        // F:验证密码是否可用
        $('#password').blur(function () {
            isPassword = false;
            $('#passwordChecked').addClass('hidden');
            var password = $(this).val();
            var regexp = /^\w{6,16}$/;
            if (regexp.test(password)) {
                $('#passwordChecked').removeClass('hidden');
                isPassword = true;
            }
        });
        // F点击确认（注册）按钮
        $('#registerBtn').click(function () {
            if (isPhoneChecked && isImageCheckCode && isCheckCode && isPassword) {
                $.post(index.URL.registerURL(), {
                        'phone': $('#registerPhone').val(),
                        'password': $('#password').val()
                    },
                    function (result) {
                        if (result) {
                            // 注册成功
                            // 隐藏底部按钮
                            $('#registerBtn').hide();
                            // 隐藏 注册 字符
                            $('h3[class="modal-title text-center"]').html('');
                            // 显示字符
                            $('#registerForm').html('<h1 class="modal-title text-center" style="color: white;">注册成功</h1>').delay(2000);
                            window.location.reload();
                        } else {
                            // 注册失败
                            // 显示字符
                            $('#registerForm').html('<h1 class="modal-title text-center" style="color: white;">注册失败</h1>');
                            // 隐藏底部按钮
                            $('#registerBtn').hide();
                            // 隐藏 注册 字符
                            $('h3[class="modal-title text-center"]').html('');
                        }
                    });
            } else {
                $('#registerBtnMessage').hide().html('<label class="label label-danger">校验未通过，无法注册!</label>').show(500).delay(3000).hide(500);
            }
        });


        /*------------------登录-----------------*/
        // F：验证手机号码 格式 以及 是否已经注册
        $('#loginPhone').blur(function () {
            var phone = $(this).val(); // 手机号码
            index.loginCheckPhone(phone);
        });
        // F:点击登录按钮
        $('#loginBtn').click(function () {
            if (isLoginPhone == false) { // 验证手机
                $('#loginBtnMessage').hide().html('<label class="label label-danger">手机校验未通过，无法登录!</label>').show(500).delay(3000).hide(500);
                return;
            }
            var password = $('#loginPassword').val(); // 验证密码
            var regexp = /^\w{6,16}$/;
            if (!regexp.test(password)) {
                $('#loginBtnMessage').hide().html('<label class="label label-danger">密码校验未通过，无法登录!</label>').show(500).delay(3000).hide(500);
                return;
            }
            var phone = $('#loginPhone').val();
            // post
            $.post(index.URL.loginURL(), {
                    'phone': phone,
                    'password': password
                },
                function (result) {
                    if (result.state == false) {
                        $('#loginBtnMessage').hide().html('<label class="label label-danger">登录失败,' + result.errorMessage + '!</label>').show(500).delay(3000).hide(500);
                    } else {
                        window.location.reload(); // 登录成功 刷新页面
                    }
                });
        });


        // F 基本信息 点击提交按钮
        $('#userInfoBtn').click(function () {
            var name = $('#name').val();
            var intro = $('#intro').val();
            var mail = $('#mail').val();
            var sex = $('input:radio[name="sex"]:checked').val();
            var regexp = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;//只能包含数字 字母 汉字的 正则
            if (name == null || name == '' || !regexp.test(name) || name.length > 10) {//校验name
                util.openMessenger('昵称不符合规范，换个喽！', 'error');
                return;
            }
            if (intro == null || intro == '') {
                util.openMessenger('简介不能为空，打点字喽！', 'error');
                return;
            }
            regexp = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
            if (mail == null || mail == '' || !regexp.test(mail)) {
                util.openMessenger('邮箱不符合规范，换个喽！', 'error');
                return;
            }
            if (sex == null || sex == '') {
                util.openMessenger('选个性别喽，假的我也不知道！', 'error');
                return;
            }
            //ajax修改用户基本信息
            $.post(index.URL.editUserInfoURL(), $('#userInfoForm').serialize(), function (result) {
                if (result) {
                    util.openMessenger('修改成功！', 'success');
                    $('#userInfoModal').modal('hide');//隐藏modal
                    $('#userInfoForm')[0].reset(); //清空表单
                }
            })
        });


        //详细信息    点击提交按钮
        $('#userDetailBtn').click(function () {
            if ($('input[name=birthdate]').val() == null || $('input[name=birthdate]').val() == '') {
                util.openMessenger('birthdate不能为空！', 'error');
                return;
            }
//    		$('#userDetailForm select').each(function(){
//    			if($(this).val() == '—— 省 ——' || $(this).val() == '—— 市 ——' || $(this).val() == '—— 区 ——'){
//    				util.openMessenger($(this).attr('name') +'不能为空！','error');
//    				return;
//    			}
//    		})
            //提交
            $.post(index.URL.editUserDetailURL(), $('#userDetailForm').serialize(), function (result) {
                util.openMessenger('修改成功！', 'success');
                $('#userDetailModal').modal('hide');//隐藏modal
                $('#userDetailForm')[0].reset(); //清空表单
            });
        });


        //好友对话框   点击发送按钮
        $('#chatBtn').click(function () {
            var message = $('#chatMessage').val();
            $('#chatMessage').val('');//清空
            if (!message) {//如果为空
                util.openMessenger('消息不能为空！', 'error');
                return;
            }
            if (message.length > 50) {
                util.openMessenger('消息过长！', 'error');
                return;
            }
            //发送ajax
            $.post(index.URL.sendChatMessageURL(), {
                userId: $('#chatUserId').attr('name'),
                message: message
            }, function (result) {
                if (!result) {
                    util.openMessenger('消息发送失败,该条消息可能无效！', 'error');
                }
            });
            //将发送的内容载入 对话框
            var $div = $('#chatDiv');
            index.fun.addMessage($div, message, 1);
        });


        //机器人聊天  点击发送
        $('#aiChatBtn').click(function () {
            var message = $('#aiChatMessage').val();
            if (!message) {//如果为空
                util.openMessenger('消息不能为空！', 'error');
                return;
            }
            if (message.length > 50) {
                util.openMessenger('消息过长！', 'error');
                return;
            }
            var $div = $('#aiChatDiv');
            //将发送的内容载入 对话框
            index.fun.addMessage($div, message, 1);
            $('#aiChatMessage').val('');//清空
            //发送ajax获取回复
            $.post(index.URL.aiChatURL(), {'message': message}, function (result) {
                if (result) {
                    index.fun.addMessage($div, result, 2);
                }
            });
        });


        //F：点击进入主页的方法
        $('#searchUserBtn').click(function () {
            var userId = $('#searchUserIdInput').val();
            //1如果 hidden is null ，提示
            if (!userId) {
                util.openMessenger('请从提示框中选择匹配的昵称！', 'info');
                return;
            }
            //2.否则，清空提示框 搜索框 并进入其主页
            $('#searchUserIdInput').val('');
            $('#searchUserInput').val('');
            window.indexBody.location.href = index.URL.userIndexURL(userId);
        });

        /*------------------------初始化END-----------------------------------------------------------------------------------------*/
    },
    // 注册相关的js
    register: {
        // F:打开注册窗口
        openRegisterModal: function () {
            isPhoneChecked = false;
            isImageCheckCode = false;
            isCheckCode = false;
            isPassword = false;
            $('#registerForm')[0].reset(); // 先清空表单
            // 按钮禁用，正确样式取消
            $('#sendCode').attr({
                'disabled': 'disabled'
            });
            $('#imageCheckCodeChecked').addClass('hidden');
            $('#registerPhoneChecked').addClass('hidden');
            $('#checkCodeChecked').addClass('hidden');
            util.openModal($('#registerModal')); // 弹出modal
        }
    },

    // 登录相关
    login: {
        // F:打开登录窗口
        openLoginModal: function () {
            $('#loginForm')[0].reset(); // 清空表单
            $('#loginPhoneChecked').addClass('hidden');
            util.openModal($('#loginModal')); // 弹出modal
        },
    },
    // F手机号码check
    // 这里之前未考虑到复用，导致要再重新写一遍
    checkPhone: function (phone) {
        isPhoneChecked = false;
        var registerPhoneChecked = $('#registerPhoneChecked'); // 正确 图标
        registerPhoneChecked.addClass('hidden');

        // 手机号码格式校验
        var regexp = /^1[34578]\d{9}$/;
        if (regexp.test(phone)) {
            // 检查手机号码是否已经注册
            $.post(index.URL.checkPhoneURL(phone), {},
                function (result) {
                    if (result == true) {
                        // 都通过
                        registerPhoneChecked.removeClass('hidden');
                        isPhoneChecked = true;
                        // 此时如果图片验证码已经验证通过，则解禁发送按钮
                        if (isImageCheckCode) {
                            $('#sendCode').removeAttr('disabled');
                        }
                    }
                });
        }
    },
    // F:登录窗口校验 手机号码
    loginCheckPhone: function (phone) {
        isLoginPhone = false;
        var loginPhoneChecked = $('#loginPhoneChecked'); // 正确 图标
        loginPhoneChecked.addClass('hidden');

        // 手机号码格式校验
        var regexp = /^1[34578]\d{9}$/;
        if (regexp.test(phone)) {
            // 检查手机号码是否已经注册
            $.post(index.URL.checkPhoneURL(phone), {},
                function (result) {
                    if (result == false) {
                        // 都通过
                        loginPhoneChecked.removeClass('hidden');
                        isLoginPhone = true;
                    }
                });
        }

    },
    // 用户相关
    user: {
        // F:注销
        userExit: function () {
            $.post(index.URL.userExitURL(), {},
                function (result) {
                    window.location.reload();
                });
        },
        //F：弹出修改基本信息modal
        userInfoModal: function (tUser) {
            if (tUser) {
                //将已有信息注入
                $('#name').val(tUser.name);
                $('#intro').val(tUser.intro);
                $('#mail').val(tUser.mail);
                $('input[value=' + tUser.sex + ']').attr('checked', 'true');
            } else {
                $.post(index.URL.queryUserInfoURL(), {}, function (tUser) {
                    $('#name').val(tUser.name);
                    $('#intro').val(tUser.intro);
                    $('#mail').val(tUser.mail);
                    $('input[value=' + tUser.sex + ']').attr('checked', 'true');
                });
            }
            util.openModal($('#userInfoModal')); // 弹出modal

        },
        // F：如果用户为设置name，自动弹出modal提醒用户设置基本信息
        autoUserInfoModal: function () {
            $.post(index.URL.queryUserInfoURL(), {}, function (result) {
                if (result) { // 如果返回值不为空
                    if (null == result.name || result.name == '' || null == result.intro || result.intro == '' ||
                        null == result.mail || result.mail == '' || null == result.sex || result.sex == '') { // 如果其中一个信息为空
                        util.openMessenger('请补全你的基本信息！', 'info');
                        index.user.userInfoModal(result);//调用弹出基本信息modal方法。
                    }
                }
            });
        },
        //F:用户上传头像
        headImageUploadModal: function () {
            util.openModal($('#headImageUploadModal'));//打开modal
        },
        //F:用户修改详细信息
        userInfoDetailModal: function () {
            //加载详细信息
            $.post(index.URL.queryUserDetailURL(), {}, function (result) {
                if (result) {
                    $('input[name=birthdate]').val(dateFormat(new Date(result.birthdate), 'yyyy-MM-dd'));
                    $('select[name=personality]').val(result.personality);
                    $('select[name=height]').val(result.height);
                    $('select[name=weight]').val(result.weight);
                    $('select[name=color]').val(result.color);
                    $('#hometown').distpicker({
                        province: result.province,
                        city: result.city,
                        district: result.district
                    });
                    //因为插件有bug。页面没有刷新的时候省不会变化
                    $('select[name=province]').val(result.province);
                    $('select[name=city]').val(result.city);
                    $('select[name=district]').val(result.district);
                }
            });
            util.openModal($('#userDetailModal'));//打开modal

        },
        //F模糊查询用户 根据name
        queryUserByNameLike: function () {
            //初始化 模糊搜索提示插件
            //这个插件本身是不支持对象的，重写了它的一些方法，总体的思路就是
            //ajax获取到List<TUser>，遍历，把每个tUser实体简化成{userId:x,name:x}的对象，再解析成string
            //因为这个插件只支持string，然后在底下，每个方法中，都把其还原成jsonObject，从中取出需要的数据
            //进入主页，获取的是hidden中的userId，显示的是name。
            $('#searchUserInput').typeahead({
                //资源获取
                source: function (query, process) {
                    return $.post(index.URL.searchNameLikeURL(), {query: query}, function (data) {
                        var tUsers = data.map(function (tUser) {
                            tUser = {userId: tUser.userId, name: tUser.name}
                            return JSON.stringify(tUser);
                        });
                        return process(tUsers);
                    });
                },
                //匹配 query和source 字符的方法
                matcher: function (obj) {
                    var item = JSON.parse(obj);
                    return ~item.name.toLowerCase().indexOf(this.query.toLowerCase())
                },
                //对匹配字符的处理
                highlighter: function (obj) {
                    var item = JSON.parse(obj);
                    var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
                    return item.name.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
                        return '<strong>' + match + '</strong>'
                    })
                },
                //选中其中一个 结果的方法
                updater: function (obj) {
                    var item = JSON.parse(obj);
                    $('#searchUserIdInput').val(item.userId);
                    return item.name;
                }
            });


        }
    },
    // 弹幕聊天相关的js
    chat: {
        init: function () {
            // F:单击发送按钮
            $('#send').click(function () {
                index.chat.sendMessage();
            });
            // F:单击回车
            $(document).keydown(function (event) {
                if (event.keyCode == 13) {
                    index.chat.sendMessage();
                }
            });
            // 建立webSocket
            if (window.WebSocket) {
                index.chat.connectWebSocket();
            } else {
                util.openMessenger('因为您的浏览器不支持WebSocket，所以只能暂时关闭弹幕功能，推荐您换个浏览器！（UC很好用）', 'error');
                $('#message').attr('disabled', 'true');
                $('#send').attr('disabled', 'true');
            }

        },

        // F:发送弹幕消息
        sendMessage: function () {
            // 1.获取消息
            var message = $('#message').val();
            if (!message) {
                util.openMessenger('消息不能为空!', 'error');
                return;
            }
            if (message.length > 15) {
                util.openMessenger('消息过长！', 'error');
                return;
            }
            $('#message').val(''); // 2.清空消息框
            var webSocketMessage = '{"type":1,"data":"' + message + '"}'; // 3.组装成弹幕消息
            try {
                webSocket.send(webSocketMessage); // 4.发送消息
            } catch (error) {
                index.chat.connectionFaild();
            }
        },

        // F:将消息显示到页面上
        showMessage: function (barrage) {
            $('body').barrager(barrage);
        },
        // F:建立与webSocket的连接
        connectWebSocket: function () {
            // var wsUri = 'ws://' + document.location.host + '/StarFire/webSocket'; // local eclipse
            var wsUri = 'ws://' + document.location.host + '/webSocket'; // local  idea
            webSocket = new WebSocket(wsUri); // 创建webSocket 对象
            // F:接收消息方法
            webSocket.onmessage = function (event) {
                var webSocketMessage = JSON.parse(event.data); // 将data转换成json对象
                // 如果是弹幕类型
                if (webSocketMessage.type == 1) {
                    var barrageResult = webSocketMessage.data; // 创建弹幕发送对象
                    var info = barrageResult.name + " : " + barrageResult.message; // 弹幕文字
                    var img = "resources/headImage/" + barrageResult.headImage; // 弹幕图片
                    if (barrageResult.state == 1) { // 如果是登录用户，附上超链接
                        var href = index.URL.userIndexURL(barrageResult.userId);
                    }
                    /*
                     * var item={ img:'static/heisenberg.png', //图片
                     * info:'弹幕文字信息', //文字 href:'http://www.yaseng.org', //链接
                     * close:true, //显示关闭按钮 speed:8, //延迟,单位秒,默认8 bottom:70,
                     * //距离底部高度,单位px,默认随机 color:'#fff', //颜色,默认白色
                     * old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色 }
                     */
                    var barrage = {
                        'info': info,
                        'img': img,
                        'href': href
                    };
                    if (barrageResult.state == 970389) {//最高权限者。的弹幕
                        var temp = {close: false, bottom: 50, color: '#f00', info: barrageResult.message};
                        barrage = $.extend(barrage, temp);
                    }
                    index.chat.showMessage(barrage);
                    //如果是好友消息类型
                } else if (webSocketMessage.type == 2) {
                    //判断  好友对话框userId 与 消息发送者 userId是否一样  且对话框打开了
                    //一样，用js刷新
                    tUser = webSocketMessage.data.tUser;
                    if ($('#chatUserId').attr('name') == tUser.userId && isChatModal == true) {
                        if (tUser.name) {
                            var name = tUser.name;
                        } else {
                            var name = tUser.phone;
                        }
                        message = '<p class="text-right col-lg-11 small list-inline">' + webSocketMessage.data.message + ':' + name + '</p>';
                        $('#chatDiv').prepend(message);
                        //并且设置双方的未读消息状态为已读
                        $.post(index.URL.changeChatStateURL(), {userId: tUser.userId}, function (result) {
                        });
                    } else {
                        index.friend.initLoadFriendList();//重新加载好友列表
                    }
                    //如果是刷新消息列表类型
                } else if (webSocketMessage.type == 3) {
                    index.message.initLoadMessageList();
                    //如果是刷新好友列表类型
                } else if (webSocketMessage.type == 4) {
                    index.friend.initLoadFriendList();
                }
            };
            // F绑定该对象的异常发生方法
            webSocket.onerror = function () {
            };
            // F当websocket关闭时的方法
            webSocket.onclose = function () {
                index.chat.connectWebSocket();
            }
            // F关闭窗口时关闭连接
            window.onbeforeunload = function () {
                webSocket.close();
            };
            // 这个方法有很大的问题,会导致浏览器或服务器崩溃
            // 重置websocket请求 防止断线。
            /*
             * var resetCountdown = setInterval(resetFunction,15000); function
             * resetFunction(){ try{ index.chat.connectWebSocket();
             * }catch(error){ clearInterval(resetCountdown);
             * index.chat.connectionFaild(); } }
             */
        },
        connectionFaild: function () {
            util.openMessenger('服务器内部错误,弹幕功能暂时关闭!请刷新页面重试!(拜拜了您)', 'error');
            $('#message').attr('disabled', 'true');
            $('#send').attr('disabled', 'true');
        },
    },
    //各个功能
    fun: {
        //F:人脸年龄测试modal
        faceAgeTestModal: function () {
            util.openModal($('#faceAgeTestModal'));
        },
        //将消息载入对话框
        addMessage: function ($div, message, type) {
            if (type == 1) {//用户发送的消息
                message = '<p class="text-left col-lg-offset-1 col-lg-11 small list-inline text-primary">' + 'you:' + message + '</p>';
                $div.prepend(message);
            } else if (type == 2) {//ai 发送的消息
                message = '<p class="text-right col-lg-11 small list-inline">' + message + ':ai' + '</p>';
                $div.prepend(message);
            }

        },
        //F机器人聊天modal
        aiChatModal: function () {
            util.openModal($('#aiChatModal'));//打开modal

        },
    },
    //消息列表处理
    message: {
        //F初始化加载消息列表
        initLoadMessageList: function () {
            $('#messageUl').load(index.URL.loadMessageListURL(), {}, function (response, status, xhr) {
                if (status != 'success') {
                    util.openMessenger('获取你的消息列表失败,你暂时无法查看消息!', 'error');
                }
            });
        },
        //F点击消息列表中的某条消息，详细查看
        messageDetailModal: function (messageType, messageId, state, userIdA,userIdB) {
            //从后台加载页面到div
            $('#messageDetailDiv').load(index.URL.messageDetailURL(),
                {type: messageType, messageId: messageId},
                function (response, status, xhr) {
                    //成功  修改未读状态  修改未读数目    打开modal
                    if (status == 'success') {
                        if (state == -1) {//如果 是  未读消息
//        					$(a).attr('style','font-style:italic');//设置这条消息的样式为已读
//        					var unreadNum = parseInt($('#unreadNum').text()) -1;//目前未读消息数目
//        					if(unreadNum <= 0){//如果为0，不显示
//        						$('#unreadNum').text('');
//        					}else{
//        						$('#unreadNum').text(unreadNum);
//        					}
                            //修改消息为已读
                            $.post(index.URL.messageChangeStateURL(), {
                                type: messageType,
                                messageId: messageId
                            }, function (result) {
                                index.message.initLoadMessageList();//直接重新加载
                            });

                        }
                        util.openModal($('#messageDetailModal'));//打开modal
                        if (messageType == 1) {
                            //点击同意
                            $('#applyYes').click(function () {
                                $.post(index.URL.messageChangeStateURL(), {
                                    type: 1,
                                    messageId: messageId,
                                    state: 2,
                                    userIdA: userIdA,
                                    userIdB: userIdB
                                }, function (result) {
                                    index.friend.initLoadFriendList();
                                });//修改申请为 同意
                                $('#messageDetailModal').modal('hide');//关闭modal
                                util.openMessenger('已接受该好友请求！', 'success');
                                index.message.initLoadMessageList();//直接重新加载

                            });
                            //点击拒绝
                            $('#applyNo').click(function () {
                                $.post(index.URL.messageChangeStateURL(), {
                                    type: 1,
                                    messageId: messageId,
                                    state: -2,
                                    userIdA: userIdA,
                                    userIdB: userIdB
                                });//修改申请为 拒绝
                                $('#messageDetailModal').modal('hide');//关闭modal
                                util.openMessenger('已拒绝该好友请求！', 'error');
                                index.message.initLoadMessageList();//直接重新加载
                            });
                        }
                    } else {//失败  提示信息
                        util.openMessenger('获取消息详情失败！', 'error');
                    }
                });
        },
    },
    //好友
    friend: {
        //F:初始化加载好友列表
        initLoadFriendList: function () {
            $('#friendUl').load(index.URL.loadFriendListURl(), {}, function (response, status, xhr) {
                if (status != 'success') {
                    util.openMessenger('加载好友列表失败！', 'error');
                }
            });
        },
        //F:打开好友对话框
        chatModal: function (userId) {
            //设置对话框 好于昵称 ，好友id
            //这个userId主要是为了判断 与某个好友的对话框是否打开，如果没有打开，直接重新加载好友列表，如果打开了，就把消息用js弄上去
            $('#chatUserId').text($('#chatUserName').text());//设置name(或phone)
            $('#chatUserId').attr('name', userId);//设置userId
            //加载聊天记录
            $('#chatDiv').load(index.URL.loadChatRecordURL(), {userId: userId}, function (response, status, xhr) {
                if (status != 'success') {
                    util.openMessenger('加载聊天记录失败！', 'error');
                } else {
                    //如果成功，且与好友间的消息状态本身为-1，修改为1
                    if ($('#chatState' + userId).text() == '!') {
                        $.post(index.URL.changeChatStateURL(), {userId: userId}, function (result) {
                            if (result) {
                                //成功后，重新加载好友列表
                                index.friend.initLoadFriendList();
                            }
                        });
                    }
                }
            });
            util.openModal($('#chatModal'));//打开聊天框

        }
    }
};


