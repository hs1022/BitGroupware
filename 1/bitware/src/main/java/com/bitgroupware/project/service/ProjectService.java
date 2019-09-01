package com.bitgroupware.project.service;

import java.util.List;

import com.bitgroupware.project.beans.MemberOfficeInfo;
import com.bitgroupware.project.beans.ProjectInfoDto;
import com.bitgroupware.project.beans.ProjectWbsDto;

public interface ProjectService {
	
	//전체 프로젝트 조회(진행중인 프로젝트)
	public List<ProjectInfoDto> selectProjectList(int prjCompletion);
	
	//완료된 프로젝트 조회
	public List<ProjectInfoDto> selectEndProjectList(int prjCompletion);
	
	//참여중인 프로젝트 조회
	public List<ProjectInfoDto> selectAttendProjectList(int prjCompletion, String memId);
	
	//프로젝트 상세페이지 조회
	public ProjectInfoDto selectProject(int prjCode);
	
	/*프로젝트 정보 수정 */
	public void updateProject(ProjectInfoDto prjDto);
	
	/*프로젝트 정보 생성 */
	public void insertProject(ProjectInfoDto prjInfoDto);
	
	/*프로젝트 참여인원 기본 리스트 출력*/
	public List<MemberOfficeInfo> selectProjectMemberList();
	
	/*프로젝트 참여인원 추가(생성)*/
	public void insertProjectAttendMembers(String memId, int prjCode);
	
	/*특정 프로젝트 참여인원 리스트 출력 */
	public List<MemberOfficeInfo> selectProjectAttendMemberList(int prjCode);
	
	/*프로젝트 WBS 정보 불러오기*/
	public List<ProjectWbsDto> selectProjectWbsList(int prjCode);
	
	/*프로젝트 WBS 삭제 */
	public boolean deleteProjectWbsList(int prjCode);
	
	/*프로젝트 WBS 생성 */
	public boolean insertProjectWbsList(List<ProjectWbsDto> prjWbsDto);
}
