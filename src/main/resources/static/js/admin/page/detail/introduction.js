$(function () {
    const Content = {
        params: {},
        load: function (params) {
            this.params = params;
            console.log(this.params)
            this.event();
        },
        event: function () {

            const paramValue = this.params.key
            console.log("paramValue" + paramValue)

            if(paramValue !== ""){
                AjaxUtil.requestBody({
                    url: '/api/introductions/findSelf',
                    data: {
                        key: paramValue,
                    },
                    success: function (data) {
                        console.log(data)
                        $(".introduceSub #category").val(data.result.items[0].category);
                        $(".introduceSub #sub_category").val(data.result.items[0].subcategory);
                        $(".introduceSub #title").val(data.result.items[0].title);
                        $(".introduceSub #sub_title").val(data.result.items[0].subtitle);
                        $(".introduceSub #contents").val(data.result.items[0].content);

                        if (data.code == 200) {
                            Swal.fire({
                                icon: 'success',
                                html: "소개글 조회하였습니다.",
                            })
                        } else {
                            Swal.fire({
                                icon: 'error',
                                html: "소개글 조회가 실패하였습니다.",
                            })
                        }
                    }
                })
            }

            const buttons = document.querySelectorAll("button");

            // 모든 버튼에 클릭 이벤트 리스너를 추가합니다.
            buttons.forEach(function (button) {
                button.addEventListener("click", function () {
                    // data-action 속성을 확인하여 해당 동작을 처리합니다.
                    const action = button.getAttribute("data-action");
                    if (action === "add") {
                        var titleValue = $("#title").val();
                        var sub_titleValue = $("#sub_title").val();
                        var contentsValue = $("#contents").val();
                        var categoryValue = $("#category").val();
                        var sub_categoryValue = $("#sub_category").val();

                        console.log("check")

                        if(paramValue === ""){

                            console.log("asdasfasf")

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
                                console.log(data)
                                if (data.code == 200) {
                                    Alert.success({text: '소개글 등록이 완료되었습니다.'}, function(){
                                        location.href = '/admin/introductions'
                                    })
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        html: "소개글 등록이 실패하였습니다.",
                                    })
                                }
                            }
                        })
                        alert("저장 동작을 수행합니다.");
                    }else if(paramValue !== ""){

                            AjaxUtil.requestBody({
                                url: '/api/introductions/update',
                                data: {
                                    title: titleValue,
                                    sub_title: sub_titleValue,
                                    contents: contentsValue,
                                    category: categoryValue,
                                    sub_category: sub_categoryValue,
                                    key: paramValue
                                },
                                success: function (data) {
                                    console.log(data)
                                    if (data.code == 200) {
                                        Swal.fire({

                                            icon: 'success',
                                            html: "소개글 등록이 완료되었습니다.",
                                        })
                                    } else {
                                        Swal.fire({
                                            icon: 'error',
                                            html: "소개글 등록이 실패하였습니다.",
                                        })
                                    }
                                }
                            })
                        }}
                    else if(action === "list"){
                        window.location.href = 'introductions'
                    }
                    else if(action === "delete"){
                        AjaxUtil.requestBody({
                            url: '/api/introductions/delete',
                            data: {
                                type: 'one',
                                id: paramValue
                            },
                            success: function (data) {
                                console.log(data)
                                if (data.code == 200) {
                                }
                            }
                        })
                    }



                });
            });

        }
    };
    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
})