#pragma once

#include <png.h>
#include <string>

class EncodingFile
{
public:
	
	EncodingFile(std::string fileName);
	~EncodingFile();
	void printRowValues();


private:
	FILE * pFile;
	png_bytepp rows;
	void setPFile(std::string fileName);
	void initValues();

	int width, height;
};


