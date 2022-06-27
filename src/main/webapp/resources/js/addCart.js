function addCart(cp, id, IpOrd, brNo, itemNo, ordCnt, status){
	
	if(!id){
		if(confirm('로그인이 필요한 서비스입니다. 로그인하시겠습니까?')){
		 	location.href=cp+"/login";
		}
	}else{
		if(IpOrd){
			if(confirm('결제 대기 상품이 있습니다. 결제창으로 이동하시겠습니까?')){
			 	location.href=cp+"/member/payment";
			}
		}else{
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					
					let data = xhr.responseText;
					let response = JSON.parse(data);
					let msg = response.msg;
					if(msg != null && msg != ''){
						alert(msg);					
					}
				}
			}
			
			xhr.open('get',cp+'/member/addcart?brNo='+brNo+'&itemNo='+itemNo+'&ordCnt='+ordCnt+'&status='+status,true);
			xhr.send();
		}
	}
}
