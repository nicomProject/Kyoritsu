$(function () {
    const Content = {
        params: {},

        load: function (params) {
            this.params = params;
            const paramValue = this.params.key
            AjaxUtil.requestBody({
                url: '/api/inquiry/findSelf/' + paramValue,
                success: function (data) {
                    console.log(data)
                    console.log(data.result.items[0])
                    console.log(data.result.items[0].inquiryName)
                    $("input[name='name']").val(data.result.items[0].inquiryName);
                    $("#phone").text(data.result.items[0].inquiryPhone);
                    $("#password").text(data.result.items[0].inquiryPwd);
                    $("#secret").text(data.result.items[0].inquirySecret);
                    $("#content-title").text(data.result.items[0].inquiryTitle);
                    $("#inquire-input").text(data.result.items[0].inquiryContent);

                    if (data.code == 200) {
                    } else {
                        Swal.fire({
                            icon: 'error',
                            html: "채용문의 조회가 실패하였습니다.",
                        })
                    }
                }
            })
            this.event();
        },
        event: function () {
        }
    }

    Content.load({
        key: $('.param[name="key"]').val() || ''
    });
});