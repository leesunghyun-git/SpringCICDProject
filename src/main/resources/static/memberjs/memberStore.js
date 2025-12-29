const {defineStore} = Pinia

const useMemberStore = defineStore('member',{
	state:()=>({
		id:'',
		name:'',
		pwd:'',
		isLogin:false
	}),
	actions:{
		async login(idRef,pwdRef){
			if(this.id==='')
			{
				idRef?.focus()
				return
			}
			if(this.pwd==='')
			{
				pwdRef?.focus()
				return
			}
			const res = await axios.get('http://localhost:8080/member/login_vue/',{
				params:{
					id:this.id,
					pwd:this.pwd
				}
			})
			if(res.data.msg==='OK')
			{
				this.id=res.data.id
				this.pwd=res.data.pwd
				this.isLogin=true
				location.href="/"
			}
			else if(res.data.msg==='NOID')
			{
				alert('아이디가 존재하지 않습니다')
				this.id=''
				this.pwd=''
			}
			else if(res.data.msg==='NOPWD')
			{
				alert('잘못된 비밀번호 입니다.')
				this.pwd=''
			}
			else{
				alert('서버 오류 발생')
			}
		},
		async logout(){
			const res = await axios.get('http://localhost:8080/member/logout_vue/',{
			})
			this.id=''
			this.pwd=''
			this.name=''
			location.href='/'
		}
	}
})