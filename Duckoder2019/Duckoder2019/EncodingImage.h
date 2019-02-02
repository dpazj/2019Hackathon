#pragma once
#include <stdio.h>
#include <string>

class EncodingImage
{
public:
	EncodingImage();
	~EncodingImage();

private:
	FILE * pFile;
	void getFile(std::string file);

};

