$(function () {
    const Content = {
        params: {},
        load: function (params) {
            this.params = params;

            Data.load({role: true});
            this.event();
        },
        event: function () {

            const buttons = document.querySelectorAll("button");

            // 모든 버튼에 클릭 이벤트 리스너를 추가합니다.
            buttons.forEach(function (button) {
                button.addEventListener("click", function () {
                    // data-action 속성을 확인하여 해당 동작을 처리합니다.
                    const action = button.getAttribute("data-action");
                    if (action === "add") {
                        var titleValue = $("#title").val();
                        var contentsValue = $("#contents").val();
                        var categoryValue = $("#category").val();
                        var sub_categoryValue = $("#sub_category").val();

                        AjaxUtil.requestBody({
                            url: '/api/introductions/add',
                            data: {
                                titleValue: titleValue,
                                contentsValue: contentsValue,
                                categoryValue: categoryValue,
                                sub_categoryValue: sub_categoryValue
                            },
                            success: function (data) {
                                console.log(data.rtn.code)
                                if (data.rtn.code == 200) {
                                    Swal.fire({
                                        icon: 'success',
                                        html: "메일로 인증번호를 보냈습니다.",
                                    })
                                    code = data.rtn.checkNum;
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        html: data.rtn.text,
                                    })
                                }
                            }
                        })

                        // "add" 동작을 처리합니다.
                        alert("저장 동작을 수행합니다.");
                        // 원하는 동작을 추가할 수 있습니다.
                    } else {
                        // 다른 동작을 처리합니다.
                        alert("다른 동작을 수행합니다.");
                        // 원하는 동작을 추가할 수 있습니다.
                    }
                });
            });

        }
    };

    Content.load();
})