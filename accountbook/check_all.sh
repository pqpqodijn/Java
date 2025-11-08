#!/bin/bash
# ===============================================
# 📦 AccountBook 프로젝트 전체 자동 점검 스크립트
# 작성자: 강현 (실무 자동 검증 루틴)
# ===============================================

# 색상 설정
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # 색상 초기화

echo -e "\n🔍 ${YELLOW}STEP 1. Clean & Compile project...${NC}"
mvn clean compile -q
if [ $? -ne 0 ]; then
  echo -e "❌ ${RED}Compilation failed!${NC}"
  echo "👉 문법 오류가 있습니다. 위의 에러 로그를 확인하세요."
  exit 1
fi
echo -e "✅ ${GREEN}Compilation success!${NC}\n"

echo -e "🧪 ${YELLOW}STEP 2. Running Unit Tests...${NC}"
mvn test -q
if [ $? -ne 0 ]; then
  echo -e "❌ ${RED}Test execution failed!${NC}"
  echo "👉 src/test/java에 있는 테스트 코드 중 오류 발생."
  exit 1
fi
echo -e "✅ ${GREEN}All tests passed!${NC}\n"

echo -e "🔎 ${YELLOW}STEP 3. Running Checkstyle Analysis...${NC}"
mvn checkstyle:check -q
if [ $? -ne 0 ]; then
  echo -e "⚠️  ${RED}Checkstyle found issues.${NC}"
  echo "👉 코드 스타일 또는 네이밍 컨벤션 문제."
  echo "   상세 보고서는 target/site/checkstyle.html 파일을 확인하세요."
  exit 1
fi
echo -e "✅ ${GREEN}Checkstyle passed!${NC}\n"

echo -e "🎉 ${GREEN}모든 검증이 완료되었습니다!${NC}"
echo "📁 프로젝트 상태: ✅ 안정적 (문법 + 테스트 + 품질 통과)"
