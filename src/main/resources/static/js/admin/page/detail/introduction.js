$(function () {
    const Content = {
        categorys: [],
        subCategorys: [],
        params: {},
        formData: {},
        load: function (params) {
            const that = this;
            const category = $("#category");
            const sub_category = $("#sub_category");
            let items = [];
            let paramValue = params.key
            let hi = ""

            AjaxUtil.request({
                url: '/api/main/setting/category',
                async: false,
                success: function (data) {
                    items = data.result.items;
                }
            });

            const categoryHash = {};
            items.map(e => e.menu).forEach(group => {
                categoryHash[group.recKey] = group;
            });
            Object.keys(categoryHash).forEach(key => {
                category.append($('<option>', {
                        value: key,
                        text: categoryHash[key].name,
                    }
                ));
            });
                const changeFunc = function(){
                    sub_category.empty();
                    items.forEach(item => {
                        if(Number(category.val()) === item.menu.recKey){
                            sub_category.append($('<option>', {
                                value: item.recKey,
                                text: item.name
                            }));
                        }
                    });
                }
            changeFunc();


            category.on('change', function() {
                    changeFunc();
                });

            if(paramValue !== ""){
                AjaxUtil.requestBody({
                    url: '/api/introductions/findSelf',
                    data: {
                        key: paramValue,
                    },
                    success: function (data) {
                        $(".pageSub #category").val(data.result.items[0].category);
                        $(".pageSub #sub_category").val(data.result.items[0].subcategory);
                        $(".pageSub #title").val(data.result.items[0].title);
                        $(".pageSub #sub_title").val(data.result.items[0].subtitle);
                        $(".pageSub #contents").val(data.result.items[0].content);
                        changeFunc();

                        if (data.code == 200) {
                        } else {
                            Swal.fire({
                                icon: 'error',
                                html: "소개글 조회가 실패하였습니다.",
                            })
                        }
                    }
                })
            }
            this.params = params;
            console.log(this.params)
            this.event();
        },

        event: function () {
            var oEditors = [];
            formData = {'title' : '제목', 'sub_title' : '소제목', 'contents' : '본문'};
            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "contents",
                sSkinURI: "/static/js/smartEditor/SmartEditor2Skin.html",
                fCreator: "createSEditor2"
            })

            function validateField(formData) {
                for (const field in formData) {
                    console.log(field)
                    const value = document.getElementById(field).value;
                    if(!value){
                        Alert.warning({text: `${formData[field]}은 필수 입력 항목입니다.`})
                        return false
                    }
                }
                return true;


            }


            const buttons = document.querySelectorAll("button");
            const paramKey = this.params.key
            // 모든 버튼에 클릭 이벤트 리스너를 추가합니다.
            buttons.forEach(function (button) {
                button.addEventListener("click", function () {
                    // data-action 속성을 확인하여 해당 동작을 처리합니다.
                    const action = button.getAttribute("data-action");
                    oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);


                    if (action === "add") {
                        var titleValue = $("#title").val();
                        var sub_titleValue = $("#sub_title").val();
                        var contentsValue = $("#contents").val();
                        var categoryValue = $("#category").val();
                        var sub_categoryValue = $("#sub_category").val();


                        if(paramKey === "" && validateField(formData)){
                            AjaxUtil.requestBody({
                                url: '/api/introductions/add',
                                data: {
                                    title: titleValue,
                                    sub_title: sub_titleValue,
                                    contents: contentsValue,
                                    category: categoryValue,
                                    sub_category: sub_categoryValue
                                },
                                success: function (data) {
                                    if(data.code === 200){
                                        Alert.success({text: data.desc}, function (){
                                            location.href = '/admin/introductions'
                                        })
                                    }else if(data.code === 210){
                                        Alert.warning({text: data.desc})
                                    }
                                    else{
                                        Alert.error({text: data.desc});
                                    }
                                }
                            })
                        }else if(paramKey !== "" && validateField(formData)){
                            AjaxUtil.requestBody({
                                url: '/api/introductions/update',
                                data: {
                                    title: titleValue,
                                    sub_title: sub_titleValue,
                                    contents: contentsValue,
                                    category: categoryValue,
                                    sub_category: sub_categoryValue,
                                    key: paramKey
                                },
                                success: function (data) {
                                    console.log(data)
                                    if(data.code === 200){
                                        Alert.success({text: data.desc}, function (){
                                            location.href = '/admin/introductions'
                                        })
                                    }else if(data.code === 210){
                                        Alert.warning({text: data.desc})
                                    }
                                    else{
                                        Alert.error({text: data.desc});
                                    }
                                }
                            })
                        }}
                    else if(action === "list"){
                        location.href = '/admin/introductions'
                    }
                    else if(action === "delete"){
                        AjaxUtil.requestBody({
                            url: '/api/introductions/delete',
                            data: {
                                type: 'one',
                                id: paramKey
                            },
                            success: function (data) {
                                console.log(data)
                                if(data.code === 200){
                                    Alert.success({text: data.desc}, function (){
                                        location.href = '/admin/introductions'
                                    })
                                }
                                else{
                                    Alert.error({text: data.desc});
                                }
                            }
                        })
                    }
                });
            });

        }
    };
    Content.load({
        key: $('.param[name="key"]').val() || '',
    });
})