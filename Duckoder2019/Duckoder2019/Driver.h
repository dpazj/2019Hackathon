#include <string>
#include <vector>
#include <iostream>
#include <fstream>



class Driver
{
	using byte = unsigned char;
public:
	Driver();
	~Driver();
	void setEncodingFile(std::string fileName);

	



private:
	unsigned int BITS_USED;
	std::vector<unsigned int> file;



};

