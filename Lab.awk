BEGIN{
	#include<studio.h>
	count=0
	{
		if ($1=="d")
		count ++
	}

	END
	{
		printf("packets dropped is %d",count)
	}	
}
