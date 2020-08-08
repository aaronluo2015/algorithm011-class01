# 双向BFS搜索模板

## 1.单向BFS搜索
```java
//求解start和end之间的最短路径
int bfs(int start,int end,int n,vector<vector<int>>&graph){
	int n=graph.size();
	int dist=0;
	vector<bool> visited(n,false);//访问标识,初始化为false

	queue<int> Q;
	Q.push(start);
	visited[start]=true;
	while(Q.empty()!=true){
		int size=Q.size();
		for(int i=0;i<size;i++){//扩展整个一层
			int pres=queue.front();
			if(nxt==end){
				return dist;
			}
			queue.pop();
			for(auto nxt:graph[pres]){
				if(visited[nxt]==false){					
					visited[nxt]=true;
					Q.push(nxt);
				}
			}
		}
		dist++;
	}
	
	return dist;
}
```
## 2.双向BFS搜索
```java
bool helper(queue<int>&Q,vector<int>&visited,vector<vector<int>>&graph){
	int size=Q.size();
	for(int i=0;i<size;i++){
		int pres=Q.front();
		Q.pop();
		for(auto nxt:graph[pre]){
			if(visited[nxt]==false){
				visited[nxt]=visited[pres];//访问标识设置成和当前一样的
				Q.push(nxt);
			}
			else if(visted[nxt]!=visted[pres]){//已访问过，访问标志不同
				return true;
			}
		}
	}
	
	return false;
}
int bfs(int start,int end,int n,vector<vector<int>>&graph){
	int n=graph.size();
	int dist=0;
	vector<bool> visited(n,false);//访问标识,初始化为false

	queue<int> head2tail,tail2head;
	head2tail.push(start);
	tail2head.push(end);
	visited[head]=1;
	visited[tail]=2;
	while(head2tail.empty()!=true||tail2head.empty()!=true){
		bool flag=false;
		//每一次都选择规模小的方向扩展
		if(head2tail.empty()==true||tail2head.size()<head2tail.size()){
			flag=helper(tail2head,visited,graph);
		}
		else if(tail2head.empty()==true||
				tail2head.size()>=head2tail.size()){
			flag=helper(head2tail,visited,graph);
		}
		
		if(flag==true){
			break;
		}
		dist++;
	}

	return dist;
}
```