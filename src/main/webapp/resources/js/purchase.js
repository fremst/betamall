function purchase(cp, id, IpOrd, brNo, itemNo, ordCnt){
	if(!id){
		if(confirm('로그인이 필요한 서비스입니다. 로그인하시겠습니까?')){
		 	location.href=cp+"/login";
		}
	}else{
		if(IpOrd){
			if(confirm('결제 대기 상품이 있습니다. 결제창으로 이동하시겠습니까?')){
			 	location.href=cp+'/member/payment';
			}
		}else{
			if(confirm('바로 구매 하시겠습니까?')){
				location.href=cp+'/member/addcart?brNo='+brNo+'&itemNo='+itemNo+'&ordCnt='+ordCnt+'&status=pur';
			}
		}
	}
}