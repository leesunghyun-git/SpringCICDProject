const {defineStore} = Pinia
/*
	<template>
		HTML
	</template>
	<script>
		data() => setup()
	</script>

*/
const useFoodStore=defineStore('food',{
	// data() => Model
	state:()=>({
		address:'마포',
		food_list:[],
		curPage:1,
		totalPage:0,
		startPage:0,
		endPage:0,
		food_detail:{},
		reply:[],
		type:1,
		cno:0,
		msg:'',
		sessionId:''
	}),
	// VM => 데이터 요청 처리
	actions:{
		async dataRecv(){
			const res = await axios.get('http://localhost:8080/food/find_vue',{
				params:{
					page:this.curPage,
					address:this.address
				}
			})
			console.log(res.data)
			this.food_list=res.data.list
			this.curPage=res.data.curPage
			this.totalPage=res.data.totalPage
			this.startPage=res.data.startPage
			this.endPage=res.data.endPage
		},
		range(start,end){
			let arr=[]
			let len = end-start
			for (let i=0;i<=len;i++)
			{
				arr.push(start)
				start++
			}
			return arr
		},
		find(addressRef){
			console.log(this.address)
			if(this.address==='')
			{
				addressRef?.focus()
				return
			}
			this.curPage=1
			this.dataRecv()
		},
		pageChange(page){
			this.curPage=page
			this.dataRecv()
		},
		async foodDetailData(fno){
			const res = await axios.get('http://localhost:8080/food/detail_vue/',{
				params:{
					fno:fno
				}
			})
			console.log(res.data)
			this.food_detail=res.data
			
		},
		// 댓글
		async foodReplyData(cno){
			const res = await axios.get('http://localhost:8080/reply/list_vue/',{
				params:{
					cno:cno,
					type:this.type
				}
			})
			console.log(res.data)
			this.reply=res.data.rList
			this.cno=res.data.cno
			this.sessionId=res.data.sessionId
		},
		async foodReplyInsert(cno,msgRef){
			console.log(cno)
			if(this.msg==='')
			{
				msgRef?.focus()
				return
			}
			
			const res=await axios.post('http://localhost:8080/reply/lnsert_vue/',{
				cno:cno,
				type:this.type,
				msg:this.msg
			})
			console.log(res.data)
			this.reply=res.data.rList
			this.cno=res.data.cno
			this.msg=''		
		}
	}
})